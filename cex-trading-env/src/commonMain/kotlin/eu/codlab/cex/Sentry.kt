package eu.codlab.cex

import eu.codlab.sentry.wrapper.Sentry

object Sentry {
    internal fun initialize(dsn: String) {
        Sentry.init {
            it.dsn = dsn
        }
    }

    suspend fun <T> trySuspend(
        onError: (Throwable) -> T? = { null },
        block: suspend () -> T
    ) = try {
        block()
    } catch (err: Throwable) {
        Sentry.captureException(err)
        onError(err)
    }
}