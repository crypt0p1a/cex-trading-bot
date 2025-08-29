package eu.codlab.cex.database.orders

import kotlinx.serialization.Serializable

@Serializable
enum class OrderType {
    LIMIT,
    MARKET
}
