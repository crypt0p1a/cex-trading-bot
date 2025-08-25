package eu.codlab.cex.database

import androidx.room.Room
import androidx.room.RoomDatabase
import eu.codlab.files.VirtualFile

internal actual fun getDatabaseBuilder(mode: DatabaseMode): RoomDatabase.Builder<AppDatabase> {
    val root = if (System.getenv().contains("TRADING_DB_PATH")) {
        VirtualFile(System.getenv("TRADING_DB_PATH"))
    } else {
        VirtualFile.Root
    }
    val dbFile = VirtualFile(root, "trading.db")

    println("using database at path ${dbFile.absolutePath}")

    return when (mode) {
        DatabaseMode.Normal -> Room.databaseBuilder<AppDatabase>(dbFile.absolutePath)
        DatabaseMode.InMemory -> Room.inMemoryDatabaseBuilder<AppDatabase>()
    }
}
