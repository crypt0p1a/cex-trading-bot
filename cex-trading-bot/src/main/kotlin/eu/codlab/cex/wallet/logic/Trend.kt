package eu.codlab.cex.wallet.logic

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.database.Database
import eu.codlab.cex.database.candle.Candle
import eu.codlab.cex.tools.extrapolate.Bar5m
import eu.codlab.cex.tools.extrapolate.Directions
import eu.codlab.cex.tools.extrapolate.Predict

class Trend(
    private val pairConfiguration: PairConfiguration,
    private val logger: Logger,
) {
    private val predict = Predict()
    suspend fun execute(): Directions {
        val candles = getCandles().map { it.toBar5m() }

        if (candles.size < 24) {
            logger.log("   can't compute an accurate prediction, size is ${candles.size}")
            return Directions.INVALID
        }

        return predict.predictWithTa4j(getCandles().map { it.toBar5m() }).also {
            logger.log("   and better prediction is $it")
        }
    }

    private suspend fun getCandles() =
        Database.candles.getDesc(pairConfiguration.left, pairConfiguration.right)
            .associateBy { it.timestamp.unixMillisLong }.map { (key, value) ->
                value
            }.sortedBy { it.timestamp.unixMillisLong }

    private fun Candle.toBar5m() = Bar5m(
        lastPrice = close.doubleValue(false),
        openPrice = open.doubleValue(false),
        closePrice = close.doubleValue(false),
        volume = volume.doubleValue(false),
        highPrice = high.doubleValue(false),
        lowPrice = low.doubleValue(false),
        timestamp = timestamp.unixMillisLong,
    )
}