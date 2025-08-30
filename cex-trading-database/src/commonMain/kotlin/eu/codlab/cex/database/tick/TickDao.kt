package eu.codlab.cex.database.tick

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import eu.codlab.cex.Symbol
import kotlinx.coroutines.flow.Flow

@Dao
interface TickDao {
    @Query("SELECT * FROM tick WHERE 'left'=:left AND 'right'=:right")
    fun flow(left: Symbol, right: Symbol): Flow<List<Tick>>

    @Query("SELECT * FROM tick WHERE 'left'=:left AND 'right'=:right")
    suspend fun getAll(left: Symbol, right: Symbol): List<Tick>

    @Query("SELECT * FROM tick WHERE `left`=:left AND `right`=:right ORDER BY id DESC LIMIT 255")
    suspend fun getDesc(left: Symbol, right: Symbol): List<Tick>

    @Insert
    suspend fun insert(tick: Tick): Long

    @Update
    suspend fun update(tick: Tick)

    @Query("DELETE FROM tick")
    suspend fun deleteTicks()
}
