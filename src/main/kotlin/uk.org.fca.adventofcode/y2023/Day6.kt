package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day5.Almanac
import uk.org.fca.adventofcode.y2023.day6.RaceResult
import uk.org.fca.adventofcode.y2023.day6.RaceResults
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

    override val number get() = 6
}
