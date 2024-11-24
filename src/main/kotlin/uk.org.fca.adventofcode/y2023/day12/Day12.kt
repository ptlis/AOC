package uk.org.fca.adventofcode.y2023.day12

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day12: Day() {
    override fun part1Solution(): BigInteger {
        return BigInteger.valueOf(
            dayData.map { ConditionRecord.parse(it) }.sumOf { it.validDamagedSpringArrangementsCount }.toLong()
        )
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val number: Int get() = 12
}