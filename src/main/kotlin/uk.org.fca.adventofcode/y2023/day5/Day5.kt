package uk.org.fca.adventofcode.y2023.day5

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day5: Day() {
    override fun part1Solution(): BigInteger {
        val almanac = Almanac.parse(dayData)

        return almanac.seeds.minOf { almanac.mapSeedToLocation(it) }
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val day get() = 5
    override val year: Int get() = 2023
}
