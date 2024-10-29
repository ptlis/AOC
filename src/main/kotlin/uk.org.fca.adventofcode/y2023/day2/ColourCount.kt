package uk.org.fca.adventofcode.y2023.day2

data class ColourCount(val count: Int, val colour: Colour) {
    enum class Colour {
        RED,
        GREEN,
        BLUE
    }

    companion object {
        fun parse (data: String): ColourCount {
            val (count, color) = data.trim().split(" ")
            return ColourCount(count.toInt(), Colour.valueOf(color.uppercase()))
        }
    }
}
