import com.ionspin.kotlin.bignum.decimal.BigDecimal
import eu.codlab.cex.Symbol
import eu.codlab.cex.database.Database
import eu.codlab.cex.database.DatabaseMode
import eu.codlab.cex.database.tick.Tick
import korlibs.time.DateTime
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestTicks {
    @BeforeTest
    fun initialize() {
        Database.mode = DatabaseMode.InMemory
    }

    @Test
    fun testInsertingSomeTick() = runTest {
        Database.ticks.insertOrUpdate(
            Tick(
                id = 0,
                exchange = "test",
                timestamp = DateTime.now(),
                left = Symbol.BTC,
                right = Symbol.EUR,
                low = BigDecimal.ZERO,
                high = BigDecimal.ZERO,
                last = BigDecimal.ZERO,
                volume = BigDecimal.ZERO,
                volume30d = BigDecimal.ZERO,
                bid = BigDecimal.ZERO,
                ask = BigDecimal.ZERO,
                priceChange = "test",
                priceChangePercent = "test"
            )
        )
    }
}