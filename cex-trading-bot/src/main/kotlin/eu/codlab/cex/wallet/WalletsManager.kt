package eu.codlab.cex.wallet

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.Pairs
import eu.codlab.cex.spot.trading.IPrivateApi
import eu.codlab.cex.spot.trading.IPublicApi
import eu.codlab.cex.spot.trading.groups.account.balance.AccountStatusRequest
import eu.codlab.cex.utils.ILoopTicker
import eu.codlab.cex.wallet.logic.Logger
import kotlin.time.Duration.Companion.minutes

class WalletsManager(
    private val publicApi: IPublicApi,
    private val privateApi: IPrivateApi,
    private val excludedWallets: List<String> = emptyList(),
    private val enabledPairsForWallets: Map<String, List<PairConfiguration>> = emptyMap()
) : ILoopTicker {
    private val logger = Logger("[WALLETS]")
    override val tickDelay = 5.minutes

    private val mutableWallets = mutableMapOf<String, WalletManager>()

    @Suppress("TooGenericExceptionCaught")
    override suspend fun tick() {
        val info = privateApi.getMyAccountStatus(AccountStatusRequest())

        info.balancesPerAccounts.keys
            .filter { it.isNotBlank() && !excludedWallets.contains(it) }
            .forEach { wallet ->
                val manager = mutableWallets.getOrPut(wallet) {
                    WalletManager(
                        wallet,
                        publicApi,
                        privateApi,
                        enabledPairForWallet = enabledPairsForWallets[wallet] ?: Pairs,
                        logger
                    )
                }

                try {
                    manager.tick()
                } catch (err: Throwable) {
                    err.printStackTrace()
                }

                logger.log("wallet $wallet managed...")
            }
    }
}
