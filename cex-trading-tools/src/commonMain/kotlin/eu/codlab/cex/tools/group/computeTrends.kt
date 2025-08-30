package eu.codlab.cex.tools.group


enum class Direction { UP, DOWN, FLAT }

data class TrendPoint(
    val ticker: Ticker,    // ticker
    val slopePerBar: Double,      // price change per bar (units of price)
    val pctPerBar: Double,        // slope normalized by mean price in window (%/bar)
    val direction: Direction
)

/**
 * Compute sliding-window trends over a list of candles.
 *
 * @param window     Size of the sliding window (>= 2).
 * @param flatTolerancePctPerBar  Absolute percentage-per-bar threshold below which the trend is FLAT.
 *                                Example: 0.05 -> flat if |pctPerBar| < 0.05% per bar.
 * @param priceSelector Function picking a representative price from a Ticker.
 *                      Default: typical price = (high + low + close)/3.
 */
fun computeTrends(
    candles: List<Ticker>,
    window: Int,
    flatTolerancePctPerBar: Double = 0.05,
    priceSelector: (Ticker) -> Double = { (it.highPrice + it.lowPrice + it.endAtPrice) / 3.0 }
): List<TrendPoint> {
    require(window >= 2) { "window must be >= 2" }
    if (candles.size < window) return emptyList()

    // Precompute representative prices
    val prices = DoubleArray(candles.size) { i -> priceSelector(candles[i]) }

    // Precompute X axis for regression within a window: 0..(window-1)
    // These sums are constant for all windows, so we can reuse them.
    val n = window.toDouble()
    val x = DoubleArray(window) { it.toDouble() }
    val sumX = x.sum()
    val sumX2 = x.sumOf { it * it }
    val denom = n * sumX2 - sumX * sumX  // denominator in slope formula (non-zero since window>=2)

    val out = ArrayList<TrendPoint>(candles.size - window + 1)

    // Slide the window and compute regression slope each time
    for (endGroup in window - 1..<candles.size) {
        var sumY = 0.0
        var sumXY = 0.0
        var sumAbsY = 0.0

        for (i in 0 until window) {
            val y = prices[endGroup - (window - 1) + i]
            sumY += y
            sumXY += x[i] * y
            sumAbsY += kotlin.math.abs(y)
        }

        val slope = (n * sumXY - sumX * sumY) / denom           // price units per bar
        val meanPrice = sumY / n
        val pctPerBar = if (meanPrice != 0.0) (slope / meanPrice) * 100.0 else 0.0

        val dir = when {
            pctPerBar > flatTolerancePctPerBar -> Direction.UP
            pctPerBar < -flatTolerancePctPerBar -> Direction.DOWN
            else -> Direction.FLAT
        }

        out += TrendPoint(
            ticker = candles[endGroup],
            slopePerBar = slope,
            pctPerBar = pctPerBar,
            direction = dir
        )
    }
    return out
}