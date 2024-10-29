package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import uk.org.fca.adventofcode.y2023.day5.Almanac

class Day5: Day() {
    override fun part1Solution(data: List<String>): BigInteger {
        val almanac = Almanac.parse(data)

        return almanac.seeds.minOf { almanac.mapSeedToLocation(it) }
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val number get() = 5
}
