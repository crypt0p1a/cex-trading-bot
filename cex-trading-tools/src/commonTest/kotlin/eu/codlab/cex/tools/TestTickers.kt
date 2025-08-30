package eu.codlab.cex.tools

import eu.codlab.cex.tools.group.DirectionRuleSet
import eu.codlab.cex.tools.group.Ticker
import eu.codlab.cex.tools.group.buildDirectionNGram
import eu.codlab.cex.tools.group.computeTrends
import eu.codlab.cex.tools.group.createRules
import org.junit.Test
import kotlin.math.max
import kotlin.math.min

class TestTickers {
    val values = listOf(
        23895.3,
        23895.2,
        23895.0,
        23895.0,
        23720.0,
        23860.0,
        23845.1,
        23710.8,
        23890.3,
        23750.6,
        23930.2,
        23805.9,
        23690.4,
        23880.0,
        23960.7,
        23770.1,
        23815.5,
        24005.8,
        23860.2,
        23725.7,
        23900.0,
        23835.4,
        23680.9,
        23855.1,
        23985.6,
        23795.3,
        23915.0,
        23700.5,
        23875.8,
        23940.2,
        23760.7,
        23895.4,
        23730.1,
        23970.9,
        23820.6,
        24955.3
    )

    @Test
    fun `default tickers`() {
        val candles = (0..values.size - 2).map {
            println("${values[it]} -> ${values[it + 1]}")
            Ticker(
                beginAtPrice = values[it],
                endAtPrice = values[it + 1],
                lowPrice = min(values[it], values[it + 1]),
                highPrice = max(values[it], values[it + 1])
            )
        }

        val window = 2  // dynamic: try 5, 10, 20, etc.
        val trends = computeTrends(candles, window, flatTolerancePctPerBar = 0.05)

        trends.forEach {
            println("${it.ticker.beginAtPrice} -> ${it.ticker.endAtPrice} -> ${it.direction}")
        }

        val seq = trends.map { it.direction }
        val model = buildDirectionNGram(seq, order = 5)

        listOf(
            trends.takeLast(6),
            trends.take(6)
        ).forEach {
            val lastTrends = it.map { it.direction }
            println("with trends which are => $lastTrends")

            val next1 = model.predictNext(history = lastTrends)
            println("next1 $next1")

            val next2 = model.predictNext(history = lastTrends)
            println("next2 $next2")

// Optional: add Laplace smoothing (e.g., 1) if your data is sparse
            val next3 = model.predictNext(history = lastTrends)
            println("next3 $next3")
        }



        println(trends)

        val windowRule = 5
        val rules = createRules(trends.map { it.direction }, windowRule)
        val ruleModel = DirectionRuleSet.build(rules)

        listOf(
            trends.takeLast(6),
            trends.take(6)
        ).forEach {
            val history = it.map { it.direction }
            println("with history $history")
            val nextDet = ruleModel.predict(history)                        // deterministic (mode)
            println("nextDet -> $nextDet")
            val nextStoch = ruleModel.predict(history, stochastic = true) // weighted random
            println("nextStoch -> $nextStoch")
        }
        /*
        val agg10min = aggregateTickers(tickers, targetIntervalMin = 10)
        println("Aggregated (10min):")
        agg10min.forEach { println(it) }



        val agg20min = aggregateTickers(tickers, targetIntervalMin = 20)
        println("Aggregated (20min):")
        agg20min.forEach { println(it) }*/
    }
}