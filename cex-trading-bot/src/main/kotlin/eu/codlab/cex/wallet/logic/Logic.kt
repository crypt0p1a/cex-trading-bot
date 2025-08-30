package eu.codlab.cex.wallet.logic

interface Logic<T> {
    suspend fun execute(previous: T)
}
