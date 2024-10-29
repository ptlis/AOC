package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day7.Game
import java.math.BigInteger

class Day7: Day {
    override fun part1Solution(data: List<String>): BigInteger {
        return Game.parse(data).winnings
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val number get() = 7
}
