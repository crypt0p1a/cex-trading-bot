package eu.codlab.cex.utils

import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode

val DecimalModeDivide = DecimalMode(
    decimalPrecision = 10,
    roundingMode = RoundingMode.FLOOR,
)