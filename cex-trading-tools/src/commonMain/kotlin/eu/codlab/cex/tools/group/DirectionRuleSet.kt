package eu.codlab.cex.tools.group

data class Rule(
    val pattern: List<Direction>,
    val result: Direction
)

fun createRules(history: List<Direction>, window: Int) =
    (window..<history.size).map { lastIndex ->
        val list = history.subList(lastIndex - window, lastIndex)
        Rule(list, history[lastIndex])
    }

/** Compiled rules with fast prediction. */
class DirectionRuleSet private constructor(
    private val countsByPattern: Map<List<Direction>, IntArray>,
    private val maxPatternLen: Int,
    private val globalCounts: IntArray
) {
    private fun IntArray.argmax(): Int =
        withIndex().maxBy { it.value }.index

    private fun IntArray.weightedSample(): Int {
        val total = sum()
        if (total <= 0) return argmax()
        val r = (1..total).random()
        var acc = 0
        for (i in indices) {
            acc += this[i]
            if (r <= acc) return i
        }
        return lastIndex
    }

    /**
     * Predict next direction by longest-suffix rule matching.
     *
     * @param history directions in chronological order (oldest -> newest)
     * @param stochastic if true, sample proportional to rule counts; else pick the mode
     * @param laplace if >0, add Laplace smoothing to matched counts (not to global fallback)
     */
    fun predict(
        history: List<Direction>,
        stochastic: Boolean = false,
        laplace: Int = 0
    ): Direction {
        val h = history
        val tryMax = minOf(h.size, maxPatternLen)
        for (k in tryMax downTo 1) {
            val key = h.takeLast(k)
            countsByPattern[key]?.let { raw ->
                val v = if (laplace > 0) intArrayOf(
                    raw[0] + laplace, raw[1] + laplace, raw[2] + laplace
                ) else raw
                val idx = if (stochastic) v.copyOf().weightedSample() else v.argmax()
                return Direction.entries[idx]
            }
        }
        // Fallback: global distribution
        val idx = if (stochastic) globalCounts.copyOf().weightedSample() else globalCounts.argmax()
        return Direction.entries[idx]
    }

    companion object {
        /** Build a compiled rule set from (pattern -> result) tuples. */
        fun build(rules: List<Rule>): DirectionRuleSet {
            require(rules.isNotEmpty()) { "rules must not be empty" }

            fun idx(d: Direction) = when (d) {
                Direction.UP -> 0; Direction.DOWN -> 1; Direction.FLAT -> 2
            }

            val map = HashMap<List<Direction>, IntArray>()
            var maxLen = 0
            val global = IntArray(3)

            for (r in rules) {
                require(r.pattern.isNotEmpty()) { "pattern must not be empty" }
                maxLen = maxOf(maxLen, r.pattern.size)
                val key = r.pattern.toList() // ensure a stable List key
                val arr = map.getOrPut(key) { IntArray(3) }
                arr[idx(r.result)]++
                global[idx(r.result)]++
            }
            return DirectionRuleSet(map, maxLen, global)
        }
    }
}