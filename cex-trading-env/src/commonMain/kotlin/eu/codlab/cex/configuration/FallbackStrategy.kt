package eu.codlab.cex.configuration

data class FallbackStrategy(
    val buy: BuyStrategy = BuyStrategy()
) {
    data class BuyStrategy(
        /**
         * Will use this ratio and apply it to the min value so that it can avoid back-fees
         */
        val pairMinRatio: Double? = null,
    )
}