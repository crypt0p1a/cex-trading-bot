package eu.codlab.cex

import eu.codlab.cex.spot.trading.PrivateApi
import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.ticks.TickManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.minutes

fun main() {
    runBlocking {
        val configuration = Configuration.load()
        val publicApi = PublicApi()
        val privateApi = PrivateApi(
            apiKey = configuration.apiKey,
            apiSecret = configuration.apiSecret
        )

        val tickManager = TickManager(
            publicApi
        )

        println(publicApi.currencyInfos())

        val job = tickManager.loop()

        println(privateApi.accountBalance())

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
