package eu.codlab.cex

import eu.codlab.cex.Configuration.Companion.extract
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestConfiguration {
    @Test
    fun `test splitting wallets only include`() {
        val line = "wallet1=ETH,BTC,LTC;wallet2=ETH,BTC"

        assertEquals(
            mapOf(
                "wallet2" to listOf(Symbol.ETH, Symbol.BTC),
                "wallet1" to listOf(Symbol.ETH, Symbol.BTC, Symbol.LTC),
            ),
            extract(line)
        )

        assertNotEquals(
            mapOf(
                "wallet2" to listOf(Symbol.ETH, Symbol.BTC, Symbol.LTC),
                "wallet1" to listOf(Symbol.ETH, Symbol.BTC)
            ),
            extract(line)
        )
    }
}