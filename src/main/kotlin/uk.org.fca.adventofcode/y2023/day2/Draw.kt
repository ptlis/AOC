package uk.org.fca.adventofcode.y2023.day2

data class Draw(val colourCounts: List<ColourCount>) {
    fun isPossible(limits: Map<ColourCount.Colour, Int>) = !colourCounts.any { it.count > limits[it.colour]!! }

    companion object {
        fun parse(data: String) = Draw(data.split(", ").map { ColourCount.parse(it) })

        fun parseDraws(data: String): List<Draw> = data.split("; ").map { Draw.parse(it) }
    }
}
