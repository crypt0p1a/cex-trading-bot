package eu.codlab.cex.utils

import kotlin.time.Duration

interface ILoopTicker {
    val tickDelay: Duration

    suspend fun tick()
}
