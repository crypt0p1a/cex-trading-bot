package eu.codlab.cex.wallet

import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.database.Database
import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.database.orders.OrderStatus
import eu.codlab.cex.spot.trading.PrivateApi
import eu.codlab.cex.spot.trading.models.OrderRequest
import eu.codlab.cex.spot.trading.models.OrderResult
import eu.codlab.cex.utils.toOrder

class WalletPairManager(
    private val wallet: String,
    private val privateApi: PrivateApi,
    private val pairConfiguration: PairConfiguration
) {
    private val left = pairConfiguration.left
    private val right = pairConfiguration.right

    suspend fun tick() {
        synchronizeOrders(pairConfiguration)

        val orders = Database.orders.getAll(wallet, left, right)
            .sortedByDescending { it.id }

        val order = orders.firstOrNull()
            ?: return manageOrderToBuy()

        // now differentiate what to do with this order...
        return when (order.status) {
            OrderStatus.PENDING_NEW -> {
                println("Order ${order.orderId} waiting to order to be validated on the broker")
            }

            OrderStatus.NEW -> {
                println("Order ${order.orderId} waiting for the order to be filled or canceled ?")
            }

            OrderStatus.PARTIALLY_FILLED -> {
                println("Order ${order.orderId} waiting for the order to be filled")
            }

            OrderStatus.FILLED -> {
                manageOrderFilled(order)
            }

            OrderStatus.EXPIRED -> {
                throw IllegalStateException("Order ${order.orderId} shouldn't be expired !")
            }

            OrderStatus.REJECTED -> {
                throw IllegalStateException("Order ${order.orderId} shouldn't be rejected !")
            }

            OrderStatus.PENDING_CANCEL -> {
                println("Order ${order.orderId} waiting for the order to be canceled")
            }

            OrderStatus.CANCELLED -> {
                if (order.side == "SELL") {
                    throw IllegalStateException("Order ${order.orderId} has been cancelled but was selling ?")
                }

                manageOrderToBuy(order)
            }
        }
    }

    private suspend fun synchronizeOrders(pair: PairConfiguration) {
        println("synchronizeOrders ${pair.leftRight}")
        val orders: List<OrderResult> = privateApi.orders(
            OrderRequest(
                pair = pair.leftRight,
                accountIds = listOf(wallet)
            )
        ).sortedByDescending { it.clientCreateTimestamp }

        orders.forEach {
            Database.orders.insertOrUpdate(it.toOrder())
        }
    }

    private suspend fun manageOrderToBuy(previousOrder: Order? = null) {
        println("manageOrderToBuy ${previousOrder?.orderId}")
    }

    private suspend fun manageOrderFilled(order: Order) {
        println("manageOrderFilled ${order.orderId}")
    }
}