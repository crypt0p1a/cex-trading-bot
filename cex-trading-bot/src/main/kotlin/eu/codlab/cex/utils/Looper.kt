package eu.codlab.cex.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class Looper(
    private val loopTicker: ILoopTicker
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun loop() = coroutineScope.async {
        coroutineScope.loop()
    }

    private suspend fun CoroutineScope.loop() {
        while (isActive) {
            loopTicker.tick()
            delay(loopTicker.tickDelay)
        }
    }

    fun shutdown() {
        coroutineScope.cancel()
    }
}