package eu.codlab.cex.candles

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.Pairs
import eu.codlab.cex.database.Database
import eu.codlab.cex.spot.trading.PublicApi
import eu.codlab.cex.spot.trading.groups.candles.CandleResolution
import eu.codlab.cex.spot.trading.groups.candles.CandlesFromPair
import eu.codlab.cex.spot.trading.models.DataType
import eu.codlab.cex.utils.ILoopTicker
import eu.codlab.cex.utils.toCandle
import korlibs.time.DateTime
import kotlin.time.Duration.Companion.minutes

class CandleManager(
    private val publicApi: PublicApi,
) : ILoopTicker {
    private suspend fun managePair(pairConfiguration: PairConfiguration) {
        try {
            val hasLast = Database.candles.getDesc(
                pairConfiguration.left,
                pairConfiguration.right
            ).isNotEmpty()

            val candles = publicApi.candles(
                CandlesFromPair(
                    pair = pairConfiguration.leftRight,
                    resolution = CandleResolution.Res5m,
                    dataType = DataType.BestAsk,
                    fromISO = null,
                    toISO = DateTime.now(),
                    limit = 100
                )
            ).filter { it.isClosed ?: false }.sortedBy { it.timestamp }

            if (candles.isEmpty()) {
                println("candle is invalid for ${pairConfiguration.leftRight}")
                return
            }

            if (hasLast) {
                val candle = candles.last()
                val mapped = candle.toCandle(pairConfiguration.left, pairConfiguration.right)

                Database.candles.insertOrUpdate(mapped)
            } else {
                candles.forEach {
                    val mapped = it.toCandle(pairConfiguration.left, pairConfiguration.right)
                    Database.candles.insertOrUpdate(mapped)
                }
            }
        } catch (err: Throwable) {
            err.printStackTrace()
        }
    }

    override val tickDelay = 5.minutes

    override suspend fun tick() {
        Pairs.forEach { managePair(it) }
    }
}
