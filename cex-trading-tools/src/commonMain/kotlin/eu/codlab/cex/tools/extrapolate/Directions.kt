package eu.codlab.cex.tools.extrapolate

data class Directions(
    val direction3h: Direction,
    val direction12h: Direction,
    val direction24h: Direction
) {
    val isDown = listOf(direction3h, direction12h, direction24h).any { it.isUp }

    companion object {
        val INVALID = Directions(
            Direction.INVALID,
            Direction.INVALID,
            Direction.INVALID
        )
    }
}
