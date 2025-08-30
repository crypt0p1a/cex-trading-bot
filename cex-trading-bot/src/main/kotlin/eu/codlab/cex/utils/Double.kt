package eu.codlab.cex.utils

import com.ionspin.kotlin.bignum.decimal.BigDecimal

fun Double.toBigDecimal() = BigDecimal.fromDouble(this)
