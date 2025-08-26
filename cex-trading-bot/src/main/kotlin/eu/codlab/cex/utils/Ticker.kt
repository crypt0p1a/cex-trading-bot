package eu.codlab.cex.utils

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
    low = low.toBigDecimal(),
    high = high.toBigDecimal(),
    last = last.toBigDecimal(),
    volume = volume.toBigDecimal(),
    volume30d = volume30d.toBigDecimal(),
    bestBid = bestBid.toBigDecimal(),
    bestAsk = bestAsk.toBigDecimal(),
    bestAskChange = bestAskChange.toBigDecimal(),
    bestAskChangePercentage = bestAskChangePercentage.toBigDecimal(),
    bestBidChange = bestBidChange.toBigDecimal(),
    bestBidChangePercentage = bestBidChangePercentage.toBigDecimal(),
    quoteVolume = quoteVolume.toBigDecimal(),
    lastTradeVolume = lastTradeVolume.toBigDecimal(),
    lastTradePrice = lastTradePrice.toBigDecimal(),
    priceChange = priceChange.toBigDecimal(),
    priceChangePercentage = priceChangePercentage.toBigDecimal(),
    lastTradeDateISO = lastTradeDateISO,
    volumeUSD = volumeUSD.toBigDecimal(),
)