import com.ionspin.kotlin.bignum.decimal.BigDecimal
import eu.codlab.cex.utils.findNearestMultiple
import kotlin.test.Test

class TestTicks {
    @Test
    fun testDecimalBigDecimal() {
        val num = BigDecimal.fromDouble(92.48817534)
        val decimalBase = BigDecimal.fromDouble(0.000001)
        val multiple = num.findNearestMultiple(decimalBase)
        println(
            "${num.toStringExpanded()} rounded to nearest multiple of " +
                    "${decimalBase.toStringExpanded()} is ${multiple.toStringExpanded()}"
        )
    }
}
