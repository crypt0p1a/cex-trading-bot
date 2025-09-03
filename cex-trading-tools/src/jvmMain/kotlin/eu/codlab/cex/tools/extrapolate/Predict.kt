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
        vol: VolumeIndicator,
        hours: Int = 1,
    ): Direction {
        val barsPerHour = (60 / 5) // 12 for 5min bars
        val window = hours * barsPerHour

        val smaFast = SMAIndicator(close, window / 4)
        val smaSlow = SMAIndicator(close, window)
        val volSma = SMAIndicator(vol, window)

        val enterRule = OverIndicatorRule(smaFast, smaSlow).and(OverIndicatorRule(vol, volSma))
        val exitRule = UnderIndicatorRule(smaFast, smaSlow).and(UnderIndicatorRule(vol, volSma))

        val i = series.endIndex
        return when {
            enterRule.isSatisfied(i) -> Direction.UP
            exitRule.isSatisfied(i) -> Direction.DOWN
            else -> if (close.getValue(i)
                    .isGreaterThan(close.getValue(i - 1))
            ) Direction.UP else Direction.DOWN
        }
    }

    @OptIn(ExperimentalTime::class)
    actual fun predictWithTa4j(bars: List<Bar5m>): Directions {
        val series = BaseBarSeriesBuilder().withName("5m").build()

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
                    .volume(it.volume)
                    .build()
            )
        }

        val close = ClosePriceIndicator(series)
        val vol = VolumeIndicator(series)

        return Directions(
            direction3h = trend(series, close, vol, 3),
            direction12h = trend(series, close, vol, 12)
        )
    }
}