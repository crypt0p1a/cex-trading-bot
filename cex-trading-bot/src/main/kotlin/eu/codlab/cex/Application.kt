package eu.codlab.cex

import eu.codlab.cex.candles.CandleManager
import eu.codlab.cex.spot.trading.PrivateApi
import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.ticks.TickManager
import eu.codlab.cex.utils.Looper
import eu.codlab.cex.utils.WrappedPublicApi
import eu.codlab.cex.wallet.WalletsManager
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

        val tickManager = Looper(
            TickManager(publicApi)
        )

        val candleManager = Looper(
            CandleManager(publicApi)
        )

        val walletsManager = Looper(
            WalletsManager(
                publicApi = WrappedPublicApi(publicApi),
                privateApi = (privateApi),
                excludedWallets = configuration.excludedWallets,
                enabledPairsForWallets = configuration.enabledPairsForWallets
            )
        )

        val jobs = listOf(
            tickManager.loop(),
            candleManager.loop(),
            walletsManager.loop()
        )

        while (true) {
            // nothing
            delay(5.minutes)
        }

        walletsManager.shutdown()
        publicApi.shutdown()
        tickManager.shutdown()

        jobs.forEach { it.await() }

        println("done")
    }

    println("returned")
}
