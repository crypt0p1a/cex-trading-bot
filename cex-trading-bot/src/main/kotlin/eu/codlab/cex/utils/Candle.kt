package eu.codlab.cex.utils

import eu.codlab.cex.Symbol
import eu.codlab.cex.spot.trading.groups.candles.Candle
import korlibs.time.DateTime


fun Candle.toCandle(left: Symbol, right: Symbol) = eu.codlab.cex.database.candle.Candle(
    id = 0,
    exchange = "cex",
    timestamp = DateTime.fromUnixMillis(timestamp),
    left = left,
    right = right,
    low = low!!.toBigDecimal(),
    high = high!!.toBigDecimal(),
    close = close!!.toBigDecimal(),
    open = open!!.toBigDecimal(),
    volume = volume!!.toBigDecimal(),
)
