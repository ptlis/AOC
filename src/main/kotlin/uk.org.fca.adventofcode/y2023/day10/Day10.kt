package uk.org.fca.adventofcode.y2023.day10

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day10: Day() {
    override fun part1Solution(): BigInteger {

        return BigInteger.valueOf(Maze.parse(dayData).stepsFromStartToFurthestPoint.toLong())
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(Maze.parse(dayData).insideCoords.size.toLong())
    }

    override val day: Int get() = 10
    override val year: Int get() = 2023
}
