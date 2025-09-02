package eu.codlab.cex.tools.extrapolate

import java.time.ZoneOffset
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toJavaInstant

data class Bar5m(
    val lastPrice: Double,
    val openPrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val closePrice: Double,
    val volume: Double,
    val timestamp: Long
) {
    @OptIn(ExperimentalTime::class)
    fun toZonedDateTime() = Instant.fromEpochMilliseconds(timestamp)
        .toJavaInstant()
        .atZone(ZoneOffset.UTC)
}
