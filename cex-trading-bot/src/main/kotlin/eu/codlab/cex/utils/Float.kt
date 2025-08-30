package eu.codlab.cex.utils

import com.ionspin.kotlin.bignum.decimal.BigDecimal

fun Float.toBigDecimal() = BigDecimal.fromFloat(this)
