package eu.codlab.cex.database

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import eu.codlab.cex.database.candle.Candle
import eu.codlab.cex.database.candle.CandleDao
import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.database.orders.OrderDao
import eu.codlab.cex.database.tick.Tick
import eu.codlab.cex.database.tick.TickDao
import eu.codlab.cex.database.utils.Converters
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [
        Tick::class,
        Order::class,
        Candle::class,
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getTickDao(): TickDao

    abstract fun getOrderDao(): OrderDao

    abstract fun getCandleDao(): CandleDao
}

internal expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal enum class DatabaseMode {
    Normal,
    InMemory
}

internal fun getDatabase(mode: DatabaseMode) = getDatabaseBuilder(mode)
    // .addMigrations(MIGRATIONS)
    // .fallbackToDestructiveMigrationOnDowngrade()
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
