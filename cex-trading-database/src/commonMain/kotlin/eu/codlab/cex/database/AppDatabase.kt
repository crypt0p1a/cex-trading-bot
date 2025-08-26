package eu.codlab.cex.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
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
    ],
    version = 1,
    autoMigrations = []
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getTickDao(): TickDao
    abstract fun getOrderDao(): OrderDao
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
