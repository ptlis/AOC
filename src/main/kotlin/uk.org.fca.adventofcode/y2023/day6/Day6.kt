package uk.org.fca.adventofcode.y2023.day6

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day6: Day() {
    override fun part1Solution(): BigInteger {
        val results = RaceResults.parse(dayData)
        return BigInteger.valueOf(results.marginOfError.toLong())
    }

    override fun part2Solution(): BigInteger {
        val result = RaceResult.parse(dayData)
        return BigInteger.valueOf(result.winningStrategyCount.toLong())
    }

    override val day get() = 6
    override val year: Int get() = 2023
}
