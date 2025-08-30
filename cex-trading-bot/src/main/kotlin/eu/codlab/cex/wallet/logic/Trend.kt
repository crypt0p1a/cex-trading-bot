package eu.codlab.cex.wallet.logic

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.database.Database
import eu.codlab.cex.tools.group.Direction
import eu.codlab.cex.tools.group.DirectionRuleSet
import eu.codlab.cex.tools.group.Ticker
import eu.codlab.cex.tools.group.computeTrends
import eu.codlab.cex.tools.group.createRules
import kotlin.math.max
import kotlin.math.min

class Trend(
    private val pairConfiguration: PairConfiguration,
    private val logger: Logger,
) {
    suspend fun execute(): Direction {
        val values = extractLastValues()
        val tickers = mapToTicker(values)

        //TODO compute the actual windowTrends & windowRule
        val windowTrends = 4
        val trends = computeTrends(tickers, windowTrends, flatTolerancePctPerBar = 0.03)

        val windowRule = 5
        val rules = createRules(trends.map { it.direction }, windowRule)
        val ruleModel = DirectionRuleSet.build(rules)

        val valuesToLookup = trends.takeLast(6)
        val history = valuesToLookup.map { it.direction }

        logger.log("trend history -> $history")
        val next = ruleModel.predict(history)
        val nextStoch = ruleModel.predict(history, stochastic = true) // weighted random
        logger.log(
            "trend : next possible value -> $next (highly probable) " +
                    "or $nextStoch (less probable)"
        )

        //TODO compute a (next, nextStoch) given a (90, 10)% vector
        return next
    }

    private suspend fun extractLastValues(): List<Double> {
        val list = Database.ticks.getDesc(pairConfiguration.left, pairConfiguration.right)

        val map = list.associateBy { it.timestamp.unixMillisLong }.map { (key, value) ->
            value
        }.sortedBy { it.timestamp.unixMillisLong }

        return map.map { it.last.doubleValue(false) }
    }

    private fun mapToTicker(values: List<Double>): List<Ticker> {
        return (0..values.size - 2).map {
            Ticker(
                beginAtPrice = values[it],
                endAtPrice = values[it + 1],
                lowPrice = min(values[it], values[it + 1]),
                highPrice = max(values[it], values[it + 1])
            )
        }
    }
}