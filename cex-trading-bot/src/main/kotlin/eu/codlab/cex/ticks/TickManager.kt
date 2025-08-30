package eu.codlab.cex.ticks

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.Pairs
import eu.codlab.cex.database.Database
import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.utils.ILoopTicker
import eu.codlab.cex.utils.toTick
import kotlin.time.Duration.Companion.minutes

class TickManager(
    private val publicApi: PublicApi
) : ILoopTicker {
    private suspend fun managePair(pairConfiguration: PairConfiguration) {
        val tick = pairConfiguration.leftRight.let { publicApi.tickers(it)[it]!! }

        val mapped = tick.toTick(pairConfiguration.left, pairConfiguration.right)

        Database.ticks.insertOrUpdate(mapped)
    }

    override val tickDelay = 5.minutes

    override suspend fun tick() {
        Pairs.forEach { managePair(it) }
    }
}
