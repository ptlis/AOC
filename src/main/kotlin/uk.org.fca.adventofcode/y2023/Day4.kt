package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day4.Scratchcards
import java.math.BigInteger

class Day4: Day() {
    override fun part1Solution(data: List<String>): BigInteger {
        return Scratchcards.parse(data).points
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return Scratchcards.parse(data).scratchCardCount
    }

    override val number get() = 4
}
