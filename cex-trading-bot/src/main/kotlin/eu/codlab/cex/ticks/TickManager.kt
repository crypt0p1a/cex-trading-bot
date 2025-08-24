package eu.codlab.cex.ticks

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.Pairs
import eu.codlab.cex.database.Database
import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.utils.toTick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.minutes

class TickManager(
    private val publicApi: PublicApi
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun loop() = coroutineScope.async {
        coroutineScope.loop()
    }

    private suspend fun CoroutineScope.loop() {
        while (isActive) {
            Pairs.forEach { managePair(it) }

            delay(5.minutes)
        }
    }

    private suspend fun managePair(pairConfiguration: PairConfiguration) {
        val tick = pairConfiguration.leftRight.let { publicApi.tickers(it)[it]!! }

        val mapped = tick.toTick(pairConfiguration.left, pairConfiguration.right)

        Database.ticks.insertOrUpdate(mapped)
    }

    fun shutdown() {
        coroutineScope.cancel()
    }
}