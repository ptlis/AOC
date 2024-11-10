package uk.org.fca.adventofcode.Common

data class Coord(val x: Int, val y: Int) {
    val north get() = Coord(x, y - 1)

    val east get() = Coord(x + 1, y)

    val south get() = Coord(x, y + 1)

    val west get() = Coord(x - 1, y)
}
