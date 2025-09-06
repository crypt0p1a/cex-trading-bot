package eu.codlab.cex.wallet

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.database.Database
import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.database.orders.OrderSide
import eu.codlab.cex.database.orders.OrderStatus
import eu.codlab.cex.spot.trading.IPrivateApi
import eu.codlab.cex.spot.trading.IPublicApi
import eu.codlab.cex.spot.trading.models.OrderRequest
import eu.codlab.cex.spot.trading.models.OrderResult
import eu.codlab.cex.tools.extrapolate.Directions
import eu.codlab.cex.utils.toOrder
import eu.codlab.cex.wallet.logic.BuyOrder
import eu.codlab.cex.wallet.logic.Logger
import eu.codlab.cex.wallet.logic.SellOrder
import eu.codlab.cex.wallet.logic.Trend
import korlibs.time.DateTime
import korlibs.time.days

class WalletPairManager(
    private val wallet: String,
    private val publicApi: IPublicApi,
    private val privateApi: IPrivateApi,
    private val pairConfiguration: PairConfiguration,
    private val enabledPairForWallet: List<PairConfiguration>,
    parent: Logger
) {
    private val logger = Logger("[${pairConfiguration.leftRight}]", parent)
    private val buyer = BuyOrder(
        wallet = wallet,
        publicApi = publicApi,
        privateApi = privateApi,
        pairConfiguration = pairConfiguration,
        logger = Logger("  ", logger),
        enabledPairForWallet = enabledPairForWallet,
    )
    private val trend = Trend(
        pairConfiguration,
        Logger("  ", logger)
    )
    private val sell = SellOrder(
        wallet = wallet,
        publicApi = publicApi,
        privateApi = privateApi,
        pairConfiguration = pairConfiguration,
        logger = Logger("  ", logger)
    )
    private val left = pairConfiguration.left
    private val right = pairConfiguration.right

    @Suppress("ThrowsCount")
    suspend fun tick() {
        val trend = trend()

        synchronizeOrders(pairConfiguration)

        val order = Database.orders.getAll(wallet, left, right)
            .maxByOrNull { it.clientCreateTimestamp } ?: return manageOrderToBuy(trend = trend)

        // now differentiate what to do with this order...
        return when (order.status) {
            OrderStatus.PENDING_NEW -> {
                logger.log("Order ${order.orderId} waiting to order to be validated on the broker")
            }

            OrderStatus.NEW -> {
                logger.log("Order ${order.orderId} waiting for the order to be filled or canceled ?")
                checkOrderState(order)
            }

            OrderStatus.PARTIALLY_FILLED -> {
                logger.log("Order ${order.orderId} waiting for the order to be filled")
            }

            OrderStatus.FILLED -> {
                manageOrderFilled(order, trend)
            }

            OrderStatus.EXPIRED -> {
                if (order.side == OrderSide.SELL) {
                    throw IllegalStateException("Order ${order.orderId} shouldn't be expired !")
                }
                manageOrderToBuy(order, trend)
            }

            OrderStatus.REJECTED -> {
                throw IllegalStateException("Order ${order.orderId} shouldn't be rejected !")
            }

            OrderStatus.PENDING_CANCEL -> {
                logger.log("Order ${order.orderId} waiting for the order to be canceled")
            }

            OrderStatus.CANCELLED -> {
                if (order.side == OrderSide.SELL) {
                    throw IllegalStateException("Order ${order.orderId} has been cancelled but was selling ?")
                }

                manageOrderToBuy(order, trend)
            }
        }
    }

    private suspend fun synchronizeOrders(pair: PairConfiguration) {
        logger.log("synchronizeOrders ${pair.leftRight}")

        val remoteOrders = privateApi.orders(
            OrderRequest(
                pair = pair.leftRight,
                accountIds = listOf(wallet)
            )
        ) + privateApi.orders(
            OrderRequest(
                pair = pair.leftRight,
                accountIds = listOf(wallet),
                archived = true,
                serverCreateTimestampFrom = DateTime.now().minus(1.days).unixMillisLong,
                serverCreateTimestampTo = DateTime.now().unixMillisLong
            )
        )

        val orders: List<OrderResult> = remoteOrders.sortedByDescending { it.clientCreateTimestamp }

        val inDatabase = Database.orders.getAll(wallet, pair.left, pair.right)

        orders.forEach { remote ->
            val insertOrUpdate = remote.toOrder().let {
                val fromDb =
                    inDatabase.find { order -> order.clientOrderId == remote.clientOrderId }

                // if we have don't have the order in database, we will create a new one
                if (null == fromDb) {
                    it
                } else {
                    // otherwise, we override the id so that it'll overwrite the database one
                    it.copy(id = fromDb.id)
                }
            }

            Database.orders.insertOrUpdate(insertOrUpdate)
        }
    }

    private suspend fun getRemoteOpenOrder(pair: PairConfiguration, clientOrderId: String) =
        privateApi.orders(
            OrderRequest(
                clientOrderId = clientOrderId,
                pair = pair.leftRight,
                accountIds = listOf(wallet),
            )
        ).firstOrNull()

    private suspend fun getRemoteArchivedOrder(pair: PairConfiguration, clientOrderId: String) =
        privateApi.orders(
            OrderRequest(
                clientOrderId = clientOrderId,
                pair = pair.leftRight,
                accountIds = listOf(wallet),
                archived = true,
                serverCreateTimestampTo = DateTime.now().minus(1.days).unixMillisLong,
                serverCreateTimestampFrom = DateTime.now().unixMillisLong
            )
        ).firstOrNull()

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    private suspend fun manageOrderToBuy(
        previousOrder: Order? = null,
        trend: Directions = Directions.INVALID
    ) = try {
        logger.log("manageOrderToBuy with previous order : $previousOrder")
        buyer.execute(previousOrder, trend)
        synchronizeOrders(pairConfiguration)
    } catch (err: Throwable) {
        // TODO sentry
    }

    private suspend fun checkOrderState(order: Order) {
        val remote = (
                getRemoteOpenOrder(pairConfiguration, order.clientOrderId)
                    ?: getRemoteArchivedOrder(pairConfiguration, order.clientOrderId)?.also {
                        logger.log("couldn't find the order, but it was archived -> $it")
                    }
                )
            ?.toOrder()
            ?.copy(id = order.id)
            ?: throw NullPointerException(
                "Couldn't find the order ${order.clientOrderId}" +
                        "even in the archive"
            )
        // if not found -> it was canceled ? but we normally get the archived

        if (remote != order) {
            logger.log("remote order state requires update")
            logger.log("previous is $order")
            logger.log("new is      $remote")
            Database.orders.insertOrUpdate(remote)
        } else {
            logger.log("remote order didn't change")
        }
    }

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    private suspend fun manageOrderFilled(order: Order, trend: Directions) {
        try {
            logger.log("manageOrderFilled ${order.orderId}")
            sell.execute(order, trend)
            synchronizeOrders(pairConfiguration)
        } catch (err: Throwable) {
            // TODO sentry
        }
    }

    private suspend fun trend() = try {
        trend.execute()
    } catch (err: Throwable) {
        err.printStackTrace()
        Directions.INVALID
    }
}
