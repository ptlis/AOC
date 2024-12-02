package uk.org.fca.adventofcode.y2023.day13

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day13: Day() {
    override fun part1Solution(): BigInteger {
        val patterns = Pattern.parse(rawDayData)

        return BigInteger.valueOf(
            Pattern.parse(rawDayData).sumOf { it.summarization }.toLong()
        )
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val day: Int get() = 13
    override val year: Int get() = 2023
}