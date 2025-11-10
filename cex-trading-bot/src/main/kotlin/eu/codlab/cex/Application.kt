package eu.codlab.cex

import eu.codlab.cex.candles.CandleManager
import eu.codlab.cex.spot.trading.PrivateApi
import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.spot.trading.calls.ApiConfiguration
import eu.codlab.cex.spot.trading.calls.RateLimitQueue
import eu.codlab.cex.spot.trading.rest.RestOptions
import eu.codlab.cex.ticks.TickManager
import eu.codlab.cex.utils.Looper
import eu.codlab.cex.wallet.WalletsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

fun main() {
    runBlocking {
        val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        val configuration = Environment.init()

        val apiConfiguration = ApiConfiguration(
            rateLimitQueue = RateLimitQueue(
                coroutineScope,
                // currently 30 - and by default but maybe actually required to be 20U
                apiTokenPoolMaximum = 30U
            )
        )

        val publicApi = PublicApi(
            coroutineScope = coroutineScope,
            apiConfiguration = apiConfiguration
        )

        val privateApi = PrivateApi(
            coroutineScope = coroutineScope,
            apiKey = configuration.apiKey,
            apiSecret = configuration.apiSecret,
            apiConfiguration = apiConfiguration,
            restOptions = RestOptions(
                rateLimitLog = { prefix, text ->
                    println("$prefix: $text")
                }
            )
        )

        val tickManager = Looper(
            TickManager(publicApi)
        )

        val candleManager = Looper(
            CandleManager(publicApi)
        )

        val walletsManager = Looper(
            WalletsManager(
                publicApi = publicApi,
                privateApi = privateApi,
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
