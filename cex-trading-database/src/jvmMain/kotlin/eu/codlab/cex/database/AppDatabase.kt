package eu.codlab.cex.database

import androidx.room.Room
import androidx.room.RoomDatabase
import eu.codlab.files.VirtualFile

internal actual fun getDatabaseBuilder(mode: DatabaseMode): RoomDatabase.Builder<AppDatabase> {
    val dbFile = VirtualFile(VirtualFile.Root, "trading.db")

    return when (mode) {
        DatabaseMode.Normal -> Room.databaseBuilder<AppDatabase>(dbFile.absolutePath)
        DatabaseMode.InMemory -> Room.inMemoryDatabaseBuilder<AppDatabase>()
    }
}
