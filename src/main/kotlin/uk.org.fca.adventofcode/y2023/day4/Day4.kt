package uk.org.fca.adventofcode.y2023.day4

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day4: Day() {
    override fun part1Solution(): BigInteger {
        return Scratchcards.parse(dayData).points
    }

    override fun part2Solution(): BigInteger {
        return Scratchcards.parse(dayData).scratchCardCount
    }

    override val number get() = 4
}
