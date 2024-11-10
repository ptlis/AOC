package uk.org.fca.adventofcode.y2023.day10

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day10: Day() {
    override fun part1Solution(): BigInteger {

        return BigInteger.valueOf(Maze.parse(dayData).stepsFromStartToFurthestPoint.toLong())
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val number: Int get() = 10
}
