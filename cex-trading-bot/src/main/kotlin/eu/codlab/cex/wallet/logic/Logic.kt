package eu.codlab.cex.wallet.logic

import eu.codlab.cex.tools.extrapolate.Directions

interface Logic<T> {
    suspend fun execute(previous: T, trend: Directions)
}
