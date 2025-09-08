package eu.codlab.cex.wallet.logic

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.Symbol
import eu.codlab.cex.database.Database
import eu.codlab.cex.spot.trading.IPrivateApi
import eu.codlab.cex.spot.trading.IPublicApi
import eu.codlab.cex.spot.trading.groups.account.balance.AccountStatusRequest
import eu.codlab.cex.spot.trading.groups.account.balance.BalanceForAccount
import eu.codlab.cex.spot.trading.groups.ticker.Ticker
import eu.codlab.cex.utils.toBigDecimal

class AccountValue(
    private val wallet: String,
    private val publicApi: IPublicApi,
    private val privateApi: IPrivateApi,
) {
    suspend fun execute(pairConfigurations: List<PairConfiguration>): BigDecimal {
        // get the overall wallet value
        val accountBalance = privateApi.getMyAccountStatus(
            AccountStatusRequest(
                accountIds = setOf(wallet),
            )
        )
        val walletBalance = accountBalance.balancesPerAccounts[wallet]!!

        // take all the RIGHT currency -> for now expects only 1 -> USD
        // TODO : convert ANYright-USD pairs
        val setOfRightCurrencies = mutableSetOf<Symbol>()
        pairConfigurations.forEach { setOfRightCurrencies.add(it.right) }

        val tickers = publicApi.tickers(pairConfigurations.map { it.leftRight })

        // extract every LEFT currency value
        val fromLeftCurrenciesOnly = pairConfigurations.map { pair ->
            walletBalance[pair.left.name]?.let { balance ->
                expectedValue(balance, pair, tickers[pair.leftRight]!!)
            } ?: BigDecimal.ZERO
        }

        assert(setOfRightCurrencies.isEmpty())
        val currencyBalance = walletBalance[setOfRightCurrencies.first().name]!!

        // calculate the "USD" balance value
        val inBalance = (currencyBalance.balance - currencyBalance.balanceOnHold).toBigDecimal()

        // and gets the LEFTs + RIGHT{1}
        return fromLeftCurrenciesOnly.reduce { acc, decimal -> acc + decimal } + inBalance
    }

    suspend fun expectedValue(
        currencyBalance: BalanceForAccount,
        pair: PairConfiguration,
        ticker: Ticker
    ): BigDecimal {
        // prepare the future with current / expected values
        val amountInOrder = Database.orders.getAll(wallet, pair.left, pair.right)
            .maxByOrNull { it.clientCreateTimestamp }?.let { order ->
                // whatever the side, we get what would be the expected account value
                // when order actually passes
                order.requestedAmountCcy1!!.multiply(order.price!!)
            } ?: BigDecimal.ZERO

        // take the remaining value not in an order for now
        val inBalance = (currencyBalance.balance - currencyBalance.balanceOnHold).toBigDecimal()

        // gets the amount in order + in wallet
        return amountInOrder + ticker.lastTradePrice.toBigDecimal().multiply(inBalance)
    }
}
