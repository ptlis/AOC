package uk.org.fca.adventofcode.y2023.day2

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day2: Day() {
    override fun part1Solution(): BigInteger {
        val limits = mapOf(
            ColourCount.Colour.RED to 12,
            ColourCount.Colour.GREEN to 13,
            ColourCount.Colour.BLUE to 14
        )

        return dayData.map { Game.parse(it) }.filter { it.isPossible(limits) }.sumOf { it.number }.toBigInteger()
    }

    override fun part2Solution(): BigInteger {
        return dayData.map { Game.parse(it) }.sumOf { it.power }.toBigInteger()
    }

    override val number get() = 2
}
