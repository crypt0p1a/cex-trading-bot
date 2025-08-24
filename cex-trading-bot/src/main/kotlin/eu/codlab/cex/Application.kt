package eu.codlab.cex

import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.ticks.TickManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.minutes

fun main() {
    runBlocking {
        val publicApi = PublicApi()

        val tickManager = TickManager(
            publicApi
        )

        println(publicApi.currencyInfos())

        val job = tickManager.loop()

        while (true) {
            // nothing
            delay(5.minutes)
        }

        publicApi.shutdown()
        tickManager.shutdown()

        job.await()

        println("done")
    }

    println("returned")
}
