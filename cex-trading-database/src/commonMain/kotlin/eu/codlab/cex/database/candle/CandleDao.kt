package eu.codlab.cex.database.candle

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import eu.codlab.cex.Symbol
import kotlinx.coroutines.flow.Flow

@Dao
interface CandleDao {
    @Query("SELECT * FROM candle WHERE 'left'=:left AND 'right'=:right")
    fun flow(left: Symbol, right: Symbol): Flow<List<Candle>>

    @Query("SELECT * FROM candle WHERE 'left'=:left AND 'right'=:right")
    suspend fun getAll(left: Symbol, right: Symbol): List<Candle>

    @Query("SELECT * FROM candle WHERE `left`=:left AND `right`=:right ORDER BY id DESC LIMIT 255")
    suspend fun getDesc(left: Symbol, right: Symbol): List<Candle>

    @Insert
    suspend fun insert(candle: Candle): Long

    @Update
    suspend fun update(candle: Candle)

    @Query("DELETE FROM candle")
    suspend fun deleteCandles()
}
