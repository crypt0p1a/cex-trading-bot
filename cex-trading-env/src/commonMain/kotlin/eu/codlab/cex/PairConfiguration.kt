package eu.codlab.cex

import eu.codlab.cex.configuration.BuySellConfiguration
import eu.codlab.cex.configuration.FallbackStrategy
import korlibs.time.TimeSpan
import korlibs.time.hours

data class PairConfiguration(
    val left: Symbol,
    val right: Symbol,
    val configuration: BuySellConfiguration,
    val allowFallbackPrice: Boolean = false,
    val minimumBalanceUsed: Double = 0.0,
    val fallbackStrategy: FallbackStrategy = FallbackStrategy(),
    val balanceWeightUsed: Int = 1,
    val cancelBuyOrderTimeout: TimeSpan = 12.hours
) {
    val leftRight = "$left-$right"
}
