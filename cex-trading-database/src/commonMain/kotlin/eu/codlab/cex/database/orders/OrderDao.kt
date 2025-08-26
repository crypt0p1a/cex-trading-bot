package eu.codlab.cex.database.orders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import eu.codlab.cex.Symbol
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM 'order' WHERE 'accountId'=:accountId AND 'currency1'=:currency1 AND 'currency2'=:currency2")
    fun flow(accountId: String, currency1: Symbol, currency2: Symbol): Flow<List<Order>>

    @Query("SELECT * FROM 'order' WHERE 'accountId'=:accountId AND 'currency1'=:currency1 AND 'currency2'=:currency2")
    suspend fun getAll(accountId: String, currency1: Symbol, currency2: Symbol): List<Order>

    @Insert
    suspend fun insert(order: Order): Long

    @Update
    suspend fun update(order: Order)

    @Query("DELETE FROM 'order'")
    suspend fun deleteOrders()
}
