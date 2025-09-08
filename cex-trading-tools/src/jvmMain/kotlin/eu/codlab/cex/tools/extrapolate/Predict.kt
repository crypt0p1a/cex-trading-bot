package eu.codlab.cex.tools.extrapolate

import org.ta4j.core.BaseBar
import org.ta4j.core.BaseBarSeries
import org.ta4j.core.BaseBarSeriesBuilder
import org.ta4j.core.indicators.SMAIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.indicators.helpers.VolumeIndicator
import org.ta4j.core.num.DecimalNum
import org.ta4j.core.rules.OverIndicatorRule
import org.ta4j.core.rules.UnderIndicatorRule
import kotlin.time.ExperimentalTime

actual class Predict {
    private fun trend(
        series: BaseBarSeries,
        close: ClosePriceIndicator,
        vol: VolumeIndicator? = null,
        hours: Int = 1,
    ): Direction {
        val barsPerHour = (60 / 5) // 12 for 5min bars
        val window = hours * barsPerHour

        val smaFast = SMAIndicator(close, window / 4)
        val smaSlow = SMAIndicator(close, window)
        val volSma = vol?.let { SMAIndicator(it, window) }

        val enterRule = OverIndicatorRule(smaFast, smaSlow).let { rule ->
            volSma?.let { rule.and(OverIndicatorRule(vol, volSma)) } ?: rule
        }
        val exitRule = UnderIndicatorRule(smaFast, smaSlow).let { rule ->
            volSma?.let { rule.and(UnderIndicatorRule(vol, volSma)) } ?: rule
        }


        val i = series.endIndex

        return when {
            enterRule.isSatisfied(i) -> Direction.UP
            exitRule.isSatisfied(i) -> Direction.DOWN
            isFlat(series, window, close, smaSlow, smaFast, i) -> Direction.FLAT
            close.getValue(i).isGreaterThan(close.getValue(i - 1)) -> Direction.UP
            else -> Direction.DOWN
        }
    }

    private fun isFlat(
        series: BaseBarSeries,
        window: Int,
        close: ClosePriceIndicator,
        smaSlow: SMAIndicator,
        smaFast: SMAIndicator,
        lastIndex: Int
    ): Boolean {
        // ---- FLAT tolerance -----------------------------------------------------
        // Base tolerance as a % of price
        val pctTol = DecimalNum.valueOf(0.02) // 2%
        val priceTol = close.getValue(lastIndex).multipliedBy(pctTol)

        // ATR-based component (adaptive). Optional.
        val atrWindow = maxOf(14, window / 2)
        val atr = org.ta4j.core.indicators.ATRIndicator(series, atrWindow)
        val atrWeight = DecimalNum.valueOf(0.25) // 25% of ATR
        val atrTol = atr.getValue(lastIndex).multipliedBy(atrWeight)

        // Final tolerance = max(price% tolerance, ATR-based tolerance)
        val tol = if (priceTol.isGreaterThan(atrTol)) priceTol else atrTol

        // Spread between fast/slow SMAs and last-bar absolute price change
        val smaSpread = smaFast.getValue(lastIndex).minus(smaSlow.getValue(lastIndex)).abs()
        val lastMove = close.getValue(lastIndex).minus(close.getValue(lastIndex - 1)).abs()

        return smaSpread.isLessThan(tol) && lastMove.isLessThan(tol)
    }

    @OptIn(ExperimentalTime::class)
    actual fun predictWithTa4j(bars: List<Bar5m>): Directions {
        val series = BaseBarSeriesBuilder().withName("5m").build()
        // note : we won't use the volume for now
        val useVolume = false // bars.filter { it.volume != 0.0 }.size > bars.size * 0.95

        bars.forEach {
            series.addBar(
                BaseBar.builder(
                    { `val`: Number? -> DecimalNum.valueOf(`val`) },
                    Number::class.java
                ).timePeriod(java.time.Duration.ofMinutes(5))
                    .endTime(it.toZonedDateTime())
                    .openPrice(it.openPrice)   // we only have lastPrice; use it for all OHLC
                    .highPrice(it.highPrice)
                    .lowPrice(it.lowPrice)
                    .closePrice(it.closePrice)
                    .let { creation ->
                        if (useVolume) {
                            creation.volume(it.volume)
                        } else {
                            creation
                        }
                    }
                    .build()
            )
        }

        val close = ClosePriceIndicator(series)
        val vol = if (useVolume) VolumeIndicator(series) else null

        return Directions(
            direction3h = trend(series, close, vol, 3),
            direction12h = trend(series, close, vol, 12),
            direction24h = trend(series, close, vol, 24)
        )
    }
}