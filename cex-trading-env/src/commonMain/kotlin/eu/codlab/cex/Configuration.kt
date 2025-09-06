package eu.codlab.cex

import eu.codlab.files.VirtualFile

data class Configuration(
    val apiKey: String,
    val apiSecret: String,
    val excludedWallets: List<String>,
    val enabledSymbolsForWallets: Map<String, List<Symbol>> = emptyMap(),
    val sentryDsn: String? = null
) {
    val enabledPairsForWallets: Map<String, List<PairConfiguration>>
        get() = enabledSymbolsForWallets.map { (key, value) ->
            key to value.map { Pairs.first { pair -> pair.right == it || pair.left == it } }
        }.toMap()

    companion object {
        internal suspend fun load(): Configuration {
            val env = VirtualFile(VirtualFile.Root, ".env")

            return if (env.exists()) {
                val map = env.readString().split("\n").filter { it.isNotBlank() }.associate {
                    val (key, value) = it.split("=")
                    key to value
                }

                Configuration(
                    apiKey = map["CEX_API_KEY"]!!,
                    apiSecret = map["CEX_API_SECRET"]!!,
                    excludedWallets = map["CEX_EXCLUDED_WALLETS"]?.split(",") ?: emptyList(),
                    enabledSymbolsForWallets = extract(map["CEX_WALLETS_ENABLED_SYMBOLS"]),
                    sentryDsn = map["SENTRY_DSN"]
                )
            } else {
                Configuration(
                    apiKey = System.getenv("CEX_API_KEY"),
                    apiSecret = System.getenv("CEX_API_SECRET"),
                    excludedWallets = System.getenv()["CEX_EXCLUDED_WALLETS"]?.split(",")
                        ?: emptyList(),
                    enabledSymbolsForWallets = extract(System.getenv()["CEX_WALLETS_ENABLED_SYMBOLS"]),
                    sentryDsn = System.getenv()["SENTRY_DSN"]
                )
            }
        }

        internal fun extract(value: String?): Map<String, List<Symbol>> {
            if (value == null || value.isBlank()) return emptyMap()

            return value.split(";").associate {
                val (name, list) = it.split(":")
                name to list.split(",").map { name ->
                    Symbol.entries.first { symbol -> symbol.name == name }
                }
            }
        }
    }
}
