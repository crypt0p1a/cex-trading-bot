package eu.codlab.cex.utils

import com.ionspin.kotlin.bignum.decimal.BigDecimal

fun BigDecimal.findNearestMultiple(base: BigDecimal): BigDecimal {
    if (base == BigDecimal.ZERO) {
        throw IllegalArgumentException("Base cannot be zero.")
    }
    var thisCopy = this.copy()
    var baseCopy = base.copy()
    var zeroes = 0

    while (!baseCopy.isWholeNumber()) {
        thisCopy = thisCopy.multiply(BigDecimal.TEN)
        baseCopy = baseCopy.multiply(BigDecimal.TEN)
        zeroes++
    }

    val ratio = thisCopy.divide(baseCopy, DecimalModeDivide)
    val roundedFactor = ratio.toStringExpanded().split(".").first().toDouble()
        .toBigDecimal()

    var intermediate = roundedFactor.multiply(baseCopy)

    repeat((0..<zeroes).count()) { intermediate = intermediate.divide(BigDecimal.TEN) }
    if (intermediate == this) {
        return this
    }

    return intermediate
}
