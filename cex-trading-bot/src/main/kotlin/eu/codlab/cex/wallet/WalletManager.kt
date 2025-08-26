package eu.codlab.cex.wallet

import eu.codlab.cex.Pairs
import eu.codlab.cex.spot.trading.PrivateApi

class WalletManager(
    private val wallet: String,
    private val privateApi: PrivateApi
) {
    private val pairManagers = Pairs.map { WalletPairManager(wallet, privateApi, it) }
    suspend fun tick() {
        println("managing $wallet")

        pairManagers.forEach { it.tick() }
    }
}