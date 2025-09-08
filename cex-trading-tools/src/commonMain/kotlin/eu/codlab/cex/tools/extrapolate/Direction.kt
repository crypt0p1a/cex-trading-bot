package eu.codlab.cex.tools.extrapolate

enum class Direction {
    DOWN,
    UP,
    FLAT,
    INVALID;

    val isUp: Boolean
        get() = this == UP

    val isDown: Boolean
        get() = !isUp
}