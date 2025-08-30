package eu.codlab.cex

import eu.codlab.files.VirtualFile

data class Configuration(
    val apiKey: String,
    val apiSecret: String,
    val excludedWallets: List<String>
) {
    companion object {
        suspend fun load(): Configuration {
            val env = VirtualFile(VirtualFile.Root, ".env")

            return if (env.exists()) {
                val map = env.readString().split("\n").filter { it.isNotBlank() }.associate {
                    val (key, value) = it.split("=")
                    key to value
                }

                Configuration(
                    apiKey = map["CEX_API_KEY"]!!,
                    apiSecret = map["CEX_API_SECRET"]!!,
                    excludedWallets = map["CEX_EXCLUDED_WALLETS"]?.split(",") ?: emptyList()
                )
            } else {
                Configuration(
                    apiKey = System.getenv("CEX_API_KEY"),
                    apiSecret = System.getenv("CEX_API_SECRET"),
                    excludedWallets = System.getenv()["CEX_EXCLUDED_WALLETS"]?.split(",")
                        ?: emptyList()
                )
            }
        }
    }
}
