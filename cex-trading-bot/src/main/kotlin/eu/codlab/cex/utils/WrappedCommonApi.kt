package eu.codlab.cex.utils

import eu.codlab.cex.spot.trading.ICommonApi
import eu.codlab.cex.spot.trading.groups.candles.CandlesFromPair
import eu.codlab.cex.spot.trading.groups.candles.CandlesFromPairs
import eu.codlab.cex.spot.trading.groups.history.trades.TradeHistoryRequestWithDate
import eu.codlab.cex.spot.trading.groups.history.trades.TradeHistoryRequestWithTrade
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

sealed class WrappedCommonApi<T : ICommonApi>(
    private val commonApi: T
) : ICommonApi {
    protected suspend fun <T> wrap(call: suspend () -> T): T {
        return try {
            val result = call()
            delay(2.seconds)
            result
        } catch (err: Throwable) {
            delay(2.seconds)
            throw err
        }
    }

    /**
     * This method allows Client to receive current order book snapshot for specific trading pair.
     *
     * https://trade.cex.io/docs/#rest-public-api-calls-order-book
     * https://trade.cex.io/docs/#rest-private-api-calls-order-book
     */
    override suspend fun orderBook(currency1: String, currency2: String) =
        wrap { commonApi.orderBook(currency1, currency2) }

    /**
     * By using Candles method Client can receive historical OHLCV candles of different resolutions
     * and data types.
     *
     * Client can indicate additional timeframe and limit filters to make response more precise to
     * Client’s requirements.
     *
     * https://trade.cex.io/docs/#rest-public-api-calls-candles
     * https://trade.cex.io/docs/#rest-private-api-calls-candles
     */
    override suspend fun candles(
        request: CandlesFromPairs
    ) = wrap { commonApi.candles(request) }

    /**
     * By using Candles method Client can receive historical OHLCV candles of different resolutions
     * and data types.
     *
     * Client can indicate additional timeframe and limit filters to make response more precise to
     * Client’s requirements.
     *
     * https://trade.cex.io/docs/#rest-public-api-calls-candles
     * https://trade.cex.io/docs/#rest-private-api-calls-candles
     */
    override suspend fun candles(
        request: CandlesFromPair
    ) = wrap { commonApi.candles(request) }

    /**
     * This method allows Client to obtain historical data as to occurred trades upon requested
     * trading pair.
     *
     * Client can supplement Trade History request with additional filter parameters, such as
     * timeframe period, tradeIds range, side etc. to receive trades which match request parameters.
     *
     * https://trade.cex.io/docs/#rest-public-api-calls-trade-history
     * https://trade.cex.io/docs/#rest-private-api-calls-trade-history
     */
    override suspend fun tradeHistory(request: TradeHistoryRequestWithTrade) =
        wrap { commonApi.tradeHistory(request) }

    /**
     * This method allows Client to obtain historical data as to occurred trades upon requested
     * trading pair.
     *
     * Client can supplement Trade History request with additional filter parameters, such as
     * timeframe period, tradeIds range, side etc. to receive trades which match request parameters.
     *
     * https://trade.cex.io/docs/#rest-public-api-calls-trade-history
     * https://trade.cex.io/docs/#rest-private-api-calls-trade-history
     */
    override suspend fun tradeHistory(request: TradeHistoryRequestWithDate) =
        wrap { commonApi.tradeHistory(request) }

    override fun shutdown() = commonApi.shutdown()
}
