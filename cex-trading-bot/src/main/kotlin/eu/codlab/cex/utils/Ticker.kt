package eu.codlab.cex.utils

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import eu.codlab.cex.Symbol
import eu.codlab.cex.database.tick.Tick
import eu.codlab.cex.spot.trading.groups.ticker.Ticker
import korlibs.time.DateTime

fun Ticker.toTick(left: Symbol, right: Symbol) = Tick(
    id = 0,
    exchange = "cex",
    timestamp = DateTime.parse(lastTradeDateISO).utc,
    left = left,
    right = right,
    low = BigDecimal.fromDouble(low),
    high = BigDecimal.fromDouble(high),
    last = BigDecimal.fromDouble(last),
    volume = BigDecimal.fromDouble(volume),
    volume30d = BigDecimal.fromDouble(volume30d),
    bestBid = BigDecimal.fromDouble(bestBid),
    bestAsk = BigDecimal.fromDouble(bestAsk),
    bestAskChange = BigDecimal.fromDouble(bestAskChange),
    bestAskChangePercentage = BigDecimal.fromFloat(bestAskChangePercentage),
    bestBidChange = BigDecimal.fromDouble(bestBidChange),
    bestBidChangePercentage = BigDecimal.fromFloat(bestBidChangePercentage),
    quoteVolume = BigDecimal.fromDouble(quoteVolume),
    lastTradeVolume = BigDecimal.fromDouble(lastTradeVolume),
    lastTradePrice = BigDecimal.fromDouble(lastTradePrice),
    priceChange = BigDecimal.fromDouble(priceChange),
    priceChangePercentage = BigDecimal.fromDouble(priceChangePercentage),
    lastTradeDateISO = lastTradeDateISO,
    volumeUSD = BigDecimal.fromDouble(volumeUSD),
)