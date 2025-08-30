package eu.codlab.cex.wallet.logic

class Logger(
    private val prefix: String,
    private val parent: Logger? = null
) {
    fun log(text: String) {
        if (null != parent) {
            parent.log("$prefix $text")
        } else {
            println("$prefix $text")
        }
    }
}
