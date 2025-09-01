package eu.codlab.cex.database

import eu.codlab.cex.database.candle.CandleController
import eu.codlab.cex.database.candle.CandleControllerImpl
import eu.codlab.cex.database.orders.OrderController
import eu.codlab.cex.database.orders.OrderControllerImpl
import eu.codlab.cex.database.tick.TickController
import eu.codlab.cex.database.tick.TickControllerImpl

object Database {
    @Suppress("ktlint:standard:backing-property-naming")
    private lateinit var _db: AppDatabase

    private val db: AppDatabase
        get() {
            if (!::_db.isInitialized) {
                _db = getDatabase(mode)
            }
            return _db
        }

    internal var mode = DatabaseMode.Normal

    private lateinit var _ticks: TickController
    val ticks: TickController
        get() {
            if (!::_ticks.isInitialized) {
                _ticks = TickControllerImpl(db)
            }
            return _ticks
        }

    private lateinit var _orders: OrderController
    val orders: OrderController
        get() {
            if (!::_orders.isInitialized) {
                _orders = OrderControllerImpl(db)
            }
            return _orders
        }

    private lateinit var _candles: CandleController
    val candles: CandleController
        get() {
            if (!::_candles.isInitialized) {
                _candles = CandleControllerImpl(db)
            }
            return _candles
        }
}
