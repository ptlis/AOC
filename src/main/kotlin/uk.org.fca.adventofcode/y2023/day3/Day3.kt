package uk.org.fca.adventofcode.y2023.day3

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day3: Day() {
    override fun part1Solution(): BigInteger {
        return PartNumber.findPartNumbers(dayData.map { it.toCharArray() }).sumOf { it.number }.toBigInteger()
    }

    override fun part2Solution(): BigInteger {
        return Cog.findCogs(PartNumber.findPartNumbers(dayData.map { it.toCharArray() })).sumOf { it.ratio }.toBigInteger()
    }

    override val day get() = 3
    override val year: Int get() = 2023
}
