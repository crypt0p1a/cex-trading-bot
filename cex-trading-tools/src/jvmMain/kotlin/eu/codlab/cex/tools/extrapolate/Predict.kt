package eu.codlab.cex.tools.extrapolate

import org.ta4j.core.BaseBar
import org.ta4j.core.BaseBarSeriesBuilder
import org.ta4j.core.indicators.SMAIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.indicators.helpers.VolumeIndicator
import org.ta4j.core.num.DecimalNum
import org.ta4j.core.rules.OverIndicatorRule
import org.ta4j.core.rules.UnderIndicatorRule
import java.time.ZonedDateTime

actual class Predict {
    actual fun predictWithTa4j(bars: List<Bar5m>): Direction {
        val series = BaseBarSeriesBuilder().withName("5m").build()

        var endTime = ZonedDateTime.now()

        bars.forEach {
            endTime = endTime + java.time.Duration.ofMinutes(5)

            series.addBar(
                BaseBar.builder(
                    { `val`: Number? -> DecimalNum.valueOf(`val`) },
                    Number::class.java
                ).timePeriod(java.time.Duration.ofMinutes(5))
                    .endTime(endTime)
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
        val smaFast = SMAIndicator(close, 3)
        val smaSlow = SMAIndicator(close, 12)
        val volSma = SMAIndicator(vol, 12)

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
}