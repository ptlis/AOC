package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day6.RaceResult
import uk.org.fca.adventofcode.y2023.day6.RaceResults
import java.math.BigInteger

class Day6: Day {
    override fun part1Solution(data: List<String>): BigInteger {
        val results = RaceResults.parse(data)
        return BigInteger.valueOf(results.marginOfError.toLong())
    }

    override fun part2Solution(data: List<String>): BigInteger {
        val result = RaceResult.parse(data)
        return BigInteger.valueOf(result.winningStrategyCount.toLong())
    }
}
