package eu.codlab.cex.wallet

import eu.codlab.cex.spot.trading.PrivateApi
import eu.codlab.cex.spot.trading.groups.account.balance.AccountStatusRequest
import eu.codlab.cex.utils.ILoopTicker
import kotlin.time.Duration.Companion.minutes

class WalletsManager(
    private val privateApi: PrivateApi
) : ILoopTicker {
    override val tickDelay = 5.minutes

    private val mutableWallets = mutableMapOf<String, WalletManager>()

    override suspend fun tick() {
        val info = privateApi.getMyAccountStatus(AccountStatusRequest())

        info.balancesPerAccounts.keys.filter { it.isNotBlank() }.forEach {
            val manager = mutableWallets.getOrPut(it) { WalletManager(it, privateApi) }


            println("wallet $it managed...")
        }
    }
}