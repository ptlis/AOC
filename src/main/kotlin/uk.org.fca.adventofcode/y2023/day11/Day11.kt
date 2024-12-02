package uk.org.fca.adventofcode.y2023.day11

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day11: Day() {
    override fun part1Solution(): BigInteger {
        return Picture.parse(dayData, 1).expanded.shortestGalaxyPaths.sumOf { it }
    }

    override fun part2Solution(): BigInteger {
        return Picture.parse(dayData, 1_000_000).expanded.shortestGalaxyPaths.sumOf { it }
    }

    override val day: Int get() = 11
    override val year: Int get() = 2023
}
