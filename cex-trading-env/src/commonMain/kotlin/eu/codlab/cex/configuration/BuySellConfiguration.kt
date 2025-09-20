package eu.codlab.cex.configuration

sealed class BuySellConfiguration {
    abstract fun toStringSell(): String

    abstract fun toStringBuy(): String

    data class Absolute(
        val buyAt: Double,
        val sellAt: Double
    ) : BuySellConfiguration() {
        override fun toStringSell() = "sellAt[$sellAt]"

        override fun toStringBuy() = "buyAt[$buyAt]"
    }


    data class Ratio(
        val buyCoef: Double,
        val sellCoef: Double
    ) : BuySellConfiguration() {
        override fun toStringSell() = "sellCoef[$sellCoef]"

        override fun toStringBuy() = "buyCoef[$buyCoef]"
    }
}