package eu.codlab.cex.database.tick

import eu.codlab.cex.Symbol
import eu.codlab.cex.database.AppDatabase
import kotlinx.coroutines.flow.Flow


interface TickController {
    suspend fun flow(left: Symbol, right: Symbol): Flow<List<Tick>>

    suspend fun getAll(left: Symbol, right: Symbol): List<Tick>

    suspend fun insertOrUpdate(tickOrUpdate: Tick): Long

    suspend fun deleteTicks()
}

internal class TickControllerImpl(database: AppDatabase) : TickController {
    private val dao = database.getTickDao()

    override suspend fun flow(left: Symbol, right: Symbol) = dao.flow(left, right)

    override suspend fun getAll(left: Symbol, right: Symbol) = dao.getAll(left, right)

    override suspend fun insertOrUpdate(tickOrUpdate: Tick): Long {
        return if (tickOrUpdate.id > 0) {
            dao.update(tickOrUpdate)
            tickOrUpdate.id
        } else {
            dao.insert(tickOrUpdate)
        }
    }

    override suspend fun deleteTicks() = dao.deleteTicks()
}
