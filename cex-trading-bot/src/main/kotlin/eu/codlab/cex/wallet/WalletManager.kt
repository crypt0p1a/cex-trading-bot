package eu.codlab.cex.wallet

import eu.codlab.cex.spot.trading.PrivateApi
import eu.codlab.cex.spot.trading.groups.account.balance.AccountStatusRequest
import eu.codlab.cex.utils.ILoopTicker
import kotlin.time.Duration.Companion.minutes

class WalletManager(
    private val wallet: String,
    private val privateApi: PrivateApi
) {
}