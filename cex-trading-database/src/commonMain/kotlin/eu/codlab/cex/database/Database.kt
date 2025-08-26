package eu.codlab.cex.database


import eu.codlab.cex.database.orders.OrderController
import eu.codlab.cex.database.orders.OrderControllerImpl
import eu.codlab.cex.database.tick.TickController
import eu.codlab.cex.database.tick.TickControllerImpl

object Database {
    private lateinit var _database: AppDatabase

    private val database: AppDatabase
        get() {
            if (!::_database.isInitialized) {
                _database = getDatabase(mode)
            }
            return _database
        }

    internal var mode = DatabaseMode.Normal

    val ticks: TickController
        get() = TickControllerImpl(database)

    val orders: OrderController
        get() = OrderControllerImpl(database)
}
