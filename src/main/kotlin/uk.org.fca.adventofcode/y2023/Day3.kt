package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day3.Cog
import uk.org.fca.adventofcode.y2023.day3.PartNumber
import java.math.BigInteger

class Day3: Day() {
    override fun part1Solution(data: List<String>): BigInteger {
        return PartNumber.findPartNumbers(data.map { it.toCharArray() }).sumOf { it.number }.toBigInteger()
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return Cog.findCogs(PartNumber.findPartNumbers(data.map { it.toCharArray() })).sumOf { it.ratio }.toBigInteger()
    }

    override val number get() = 3
}
