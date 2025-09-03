package eu.codlab.cex.tools.extrapolate

data class Directions(
    val direction3h: Direction,
    val direction12h: Direction
) {
    val isDown = direction3h == Direction.DOWN && direction12h == Direction.DOWN

    companion object {
        val INVALID = Directions(Direction.INVALID, Direction.INVALID)
    }
}
