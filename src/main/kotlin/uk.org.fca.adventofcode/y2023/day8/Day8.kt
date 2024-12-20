package uk.org.fca.adventofcode.y2023.day8

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day8: Day() {
    override fun part1Solution(): BigInteger {
        return NavigationInstructions.parse(dayData).navigate()
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val day: Int get() = 8
    override val year: Int get() = 2023
}
