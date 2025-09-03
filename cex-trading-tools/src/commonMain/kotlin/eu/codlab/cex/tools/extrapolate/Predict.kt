package eu.codlab.cex.tools.extrapolate

expect class Predict {
    fun predictWithTa4j(bars: List<Bar5m>): Directions
}
