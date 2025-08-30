package eu.codlab.cex.database.orders

import eu.codlab.cex.Symbol
import eu.codlab.cex.database.AppDatabase
import kotlinx.coroutines.flow.Flow

interface OrderController {
    suspend fun flow(accountId: String, left: Symbol, right: Symbol): Flow<List<Order>>

    suspend fun getAll(accountId: String, left: Symbol, right: Symbol): List<Order>

    suspend fun insertOrUpdate(insertOrUpdate: Order): Long

    suspend fun deleteOrders()
}

internal class OrderControllerImpl(database: AppDatabase) : OrderController {
    private val dao = database.getOrderDao()

    override suspend fun flow(accountId: String, left: Symbol, right: Symbol) =
        dao.flow(accountId, left, right)

    override suspend fun getAll(accountId: String, left: Symbol, right: Symbol) =
        dao.getAll(accountId, left, right)

    override suspend fun insertOrUpdate(insertOrUpdate: Order) =
        if (insertOrUpdate.id > 0) {
            dao.update(insertOrUpdate)
            insertOrUpdate.id
        } else {
            dao.insert(insertOrUpdate)
        }

    override suspend fun deleteOrders() = dao.deleteOrders()
}
