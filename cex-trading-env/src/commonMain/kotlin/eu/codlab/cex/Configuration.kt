package eu.codlab.cex

import eu.codlab.files.VirtualFile

data class Configuration(
    val apiKey: String,
    val apiSecret: String,
) {
    companion object {
        suspend fun load(): Configuration {
            val env = VirtualFile(VirtualFile.Root, ".env")

            return if (env.exists()) {
                val map = env.readString().split("\n").filter { it.isNotBlank() }.associate {
                    println(it)
                    val (key, value) = it.split("=")
                    key to value
                }

                Configuration(
                    apiKey = map["CEX_API_KEY"]!!,
                    apiSecret = map["CEX_API_SECRET"]!!,
                )
            } else {
                Configuration(
                    apiKey = System.getenv("CEX_API_KEY"),
                    apiSecret = System.getenv("CEX_API_SECRET")
                )
            }
        }
    }
}