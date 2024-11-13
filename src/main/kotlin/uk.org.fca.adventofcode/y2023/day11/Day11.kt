package uk.org.fca.adventofcode.y2023.day11

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day11: Day() {
    override fun part1Solution(): BigInteger {
        return BigInteger.valueOf(Picture.parse(dayData).expanded.shortestGalaxyPaths.sum().toLong())
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val number: Int
        get() = 11
}
