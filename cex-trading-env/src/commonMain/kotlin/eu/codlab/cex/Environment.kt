package eu.codlab.cex

object Environment {
    suspend fun init() = Configuration.load().also {
        it.sentryDsn?.let { dsn -> Sentry.initialize(dsn) }
    }
}