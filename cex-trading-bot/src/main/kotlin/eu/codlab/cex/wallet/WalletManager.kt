package eu.codlab.cex.wallet

import eu.codlab.cex.Pairs
import eu.codlab.cex.spot.trading.IPrivateApi
import eu.codlab.cex.spot.trading.IPublicApi
import eu.codlab.cex.wallet.logic.AccountValue
import eu.codlab.cex.wallet.logic.Logger

class WalletManager(
    private val wallet: String,
    private val publicApi: IPublicApi,
    private val privateApi: IPrivateApi,
    parent: Logger
) {
    private val logger = Logger("[$wallet] ", parent)
    private val pairs = Pairs
    private val pairManagers = pairs.map { pairConfiguration ->
        WalletPairManager(
            wallet,
            publicApi,
            privateApi,
            pairConfiguration,
            logger
        )
    }

    private val accountValue = AccountValue(
        wallet,
        publicApi,
        privateApi,
    )

    suspend fun tick() {
        logger.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        logger.log("  managing $wallet")

        pairManagers.forEach { it.tick() }

        try {
            val expectedValues = accountValue.execute(pairs)
            logger.log(" $wallet expected value ${expectedValues.toStringExpanded()}")
        } catch (_: Throwable) {
            // until stability is checked and Sentry plugged in, just skip
        }
        logger.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
    }
}
