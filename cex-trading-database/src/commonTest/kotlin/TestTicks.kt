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
                bestAsk = BigDecimal.ZERO,
                bestBid = BigDecimal.ZERO,
                bestAskChange = BigDecimal.ZERO,
                bestBidChange = BigDecimal.ZERO,
                bestAskChangePercentage = BigDecimal.ZERO,
                bestBidChangePercentage = BigDecimal.ZERO,
                quoteVolume = BigDecimal.ZERO,
                lastTradePrice = BigDecimal.ZERO,
                lastTradeVolume = BigDecimal.ZERO,
                priceChange = BigDecimal.ZERO,
                priceChangePercentage = BigDecimal.ZERO,
                lastTradeDateISO = "",
                volumeUSD = BigDecimal.ZERO,
            )
        )
    }
}
