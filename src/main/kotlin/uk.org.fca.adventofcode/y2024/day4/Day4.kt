package uk.org.fca.adventofcode.y2024.day4

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.common.extractDiagonals
import uk.org.fca.adventofcode.common.mirrorGridVertically
import uk.org.fca.adventofcode.common.rotateStringGrid
import java.math.BigInteger

class Day4: Day() {
    override fun part1Solution(): BigInteger =
        BigInteger.valueOf(solvePart1(dayData).toLong())

    override fun part2Solution(): BigInteger =
        BigInteger.valueOf(solvePart2(dayData).toLong())

    override val day: Int get() = 4
    override val year: Int get() = 2024

    companion object {
        private val xmasRegex = "XMAS".toRegex()

        fun solvePart1(rawData: List<String>): Int = (
            rawData.sumOf { findVerticalXmases(it) }
            + rotateStringGrid(rawData).sumOf { findVerticalXmases(it) }
            + extractDiagonals(rawData).sumOf { findVerticalXmases(it) }
            + extractDiagonals(mirrorGridVertically(rawData)).sumOf { findVerticalXmases(it) }
        )

        fun solvePart2(rawData: List<String>): Int =
            -1

        fun findVerticalXmases(line: String): Int {
            return xmasRegex.findAll(line).toList().size + xmasRegex.findAll(line.reversed()).toList().size
        }
    }
}
