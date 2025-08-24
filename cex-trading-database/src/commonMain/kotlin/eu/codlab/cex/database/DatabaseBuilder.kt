package eu.codlab.cex.database

import androidx.room.RoomDatabase

internal expect fun getDatabaseBuilder(mode: DatabaseMode): RoomDatabase.Builder<AppDatabase>
