package eu.codlab.cex.tools.group


class DirectionNGram(
    private val order: Int,
    private val counts: Map<List<Direction>, IntArray>,
    private val globalCounts: IntArray
) {
    private fun IntArray.argmax(): Int =
        withIndex().maxBy { it.value }.index

    private fun IntArray.weightedSample(): Int {
        val total = this.sum()
        if (total == 0) return this.argmax()
        val r = (1..total).random()
        var acc = 0
        for (i in indices) {
            acc += this[i]
            if (r <= acc) return i
        }
        return lastIndex
    }

    /**
     * Predict next direction given recent history.
     *
     * @param history  the most recent directions in chronological order (oldest -> newest)
     * @param stochastic if true, sample proportionally to counts; otherwise pick the mode
     * @param laplace if >0, apply Laplace smoothing when evaluating a match
     */
    fun predictNext(
        history: List<Direction>,
        stochastic: Boolean = false,
        laplace: Int = 0
    ): Direction {
        // Try backoff: order, order-1, ..., 1
        for (k in order downTo 1) {
            if (history.size < k) continue
            val key = history.takeLast(k)
            counts[key]?.let { raw ->
                val v = if (laplace > 0) intArrayOf(
                    raw[0] + laplace, raw[1] + laplace, raw[2] + laplace
                ) else raw
                val idx = if (stochastic) v.copyOf().weightedSample() else v.argmax()
                return Direction.entries[idx]
            }
        }
        // Fallback: global distribution (majority or sample)
        val idx = if (stochastic) globalCounts.copyOf().weightedSample() else globalCounts.argmax()
        return Direction.entries[idx]
    }
}

/** Trainer */
fun buildDirectionNGram(sequence: List<Direction>, order: Int): DirectionNGram {
    require(order >= 1) { "order must be >= 1" }
    require(sequence.size >= order + 1) { "sequence must have at least order+1 items" }

    // Map key -> counts [UP, DOWN, FLAT]
    val map = HashMap<List<Direction>, IntArray>()
    val global = IntArray(3)

    fun idx(d: Direction) = when (d) {
        Direction.UP -> 0; Direction.DOWN -> 1; Direction.FLAT -> 2
    }

    for (i in 0 until sequence.size - order) {
        val key = sequence.subList(i, i + order).toList()
        val next = sequence[i + order]
        val arr = map.getOrPut(key) { IntArray(3) }
        arr[idx(next)]++
        global[idx(next)]++
    }

    return DirectionNGram(order, map, global)
}
