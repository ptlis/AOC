package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day8.NavigationInstructions
import java.math.BigInteger

class Day8: Day() {
    override fun part1Solution(): BigInteger {
        return NavigationInstructions.parse(dayData).navigate("AAA", "ZZZ")
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val number: Int get() = 8
}
