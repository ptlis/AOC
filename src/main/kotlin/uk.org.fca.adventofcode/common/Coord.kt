package uk.org.fca.adventofcode.common

import kotlin.math.abs

data class Coord(val x: Int, val y: Int) {
    val north get() = Coord(x, y - 1)

    val east get() = Coord(x + 1, y)

    val south get() = Coord(x, y + 1)

    val west get() = Coord(x - 1, y)

    fun distanceTo(target: Coord) = abs(this.x - target.x) + abs(this.y - target.y)

    override fun toString() = "$x,$y"

    operator fun plus(coord: Coord) = Coord(x + coord.x, y + coord.y)
}
