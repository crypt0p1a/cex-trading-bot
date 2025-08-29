package eu.codlab.cex

import korlibs.time.TimeSpan
import korlibs.time.hours

data class PairConfiguration(
    val left: Symbol,
    val right: Symbol,
    val buyCoef: Double,
    val sellCoef: Double,
    val allowFallbackPrice: Boolean = false,
    val minimumBalanceUsed: Double = 0.0,
    val balanceWeightUsed: Int = 1,
    val cancelBuyOrderTimeout: TimeSpan = 12.hours
) {
    val leftRight = "$left-$right"
}
