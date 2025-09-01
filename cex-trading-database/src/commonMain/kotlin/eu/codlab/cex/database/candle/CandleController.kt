package eu.codlab.cex.database.candle

import eu.codlab.cex.Symbol
import eu.codlab.cex.database.AppDatabase
import kotlinx.coroutines.flow.Flow

interface CandleController {
    suspend fun flow(left: Symbol, right: Symbol): Flow<List<Candle>>

    suspend fun getAll(left: Symbol, right: Symbol): List<Candle>

    suspend fun getDesc(left: Symbol, right: Symbol): List<Candle>

    suspend fun insertOrUpdate(candleOrUpdate: Candle): Long

    suspend fun deleteCandles()
}

internal class CandleControllerImpl(database: AppDatabase) : CandleController {
    private val dao = database.getCandleDao()

    override suspend fun flow(left: Symbol, right: Symbol) = dao.flow(left, right)

    override suspend fun getAll(left: Symbol, right: Symbol) = dao.getAll(left, right)

    override suspend fun getDesc(left: Symbol, right: Symbol) = dao.getDesc(left, right)

    override suspend fun insertOrUpdate(candleOrUpdate: Candle) =
        if (candleOrUpdate.id > 0) {
            dao.update(candleOrUpdate)
            candleOrUpdate.id
        } else {
            dao.insert(candleOrUpdate)
        }

    override suspend fun deleteCandles() = dao.deleteCandles()
}
