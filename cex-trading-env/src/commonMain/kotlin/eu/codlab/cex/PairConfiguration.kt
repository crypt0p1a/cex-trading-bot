package eu.codlab.cex

data class PairConfiguration(
    val left: Symbol,
    val right: Symbol,
    val buyCoef: Double,
    val sellCoef: Double,
    val allowFallbackPrice: Boolean = false,
    val minimumBalanceUsed: Double = 0.0,
    val balanceWeightUsed: Int = 1,
    val cancelBuyOrderTimeoutMinutes: Int = 1440
) {
    val leftRight = "$left-$right"
}
