package eu.codlab.cex.wallet.logic

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.database.Database
import eu.codlab.cex.database.tick.Tick
import eu.codlab.cex.tools.extrapolate.Bar5m
import eu.codlab.cex.tools.extrapolate.Direction
import eu.codlab.cex.tools.extrapolate.Predict

class Trend(
    private val pairConfiguration: PairConfiguration,
    private val logger: Logger,
) {
    suspend fun execute(): Direction {
        val predict = Predict()
        var previous: Tick? = null

        val bypassedTickers = getTickers().mapNotNull { ticker ->
            if (previous == null) {
                previous = ticker
                null
            } else {
                Bar5m(
                    lastPrice = ticker.last.doubleValue(false),
                    openPrice = previous.last.doubleValue(false),
                    closePrice = ticker.last.doubleValue(false),
                    volume = ticker.volume.doubleValue(false),
                    highPrice = ticker.high.doubleValue(false),
                    lowPrice = ticker.low.doubleValue(false),
                ).also {
                    previous = ticker
                }
            }
        }

        return predict.predictWithTa4j(bypassedTickers).also {
            logger.log("   and better prediction is $it")
        }
    }

    private suspend fun getTickers() =
        Database.ticks.getDesc(pairConfiguration.left, pairConfiguration.right)
            .associateBy { it.timestamp.unixMillisLong }.map { (key, value) ->
                value
            }.sortedBy { it.timestamp.unixMillisLong }
}