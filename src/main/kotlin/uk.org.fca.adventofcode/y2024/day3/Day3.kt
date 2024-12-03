package uk.org.fca.adventofcode.y2024.day3

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2024.day2.Day2.Companion.isLevelValid
import java.math.BigInteger

class Day3: Day() {
    override fun part1Solution(): BigInteger {
        return BigInteger.valueOf(solvePart1(dayData).toLong())
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    override val day: Int get() = 3
    override val year: Int get() = 2024

    companion object {

        fun solvePart1(rawData: List<String>): Int =
            extractMultiplyStatements(rawData).sumOf { it.first * it.second }

        fun extractMultiplyStatements(rawData: List<String>): List<Pair<Int, Int>> {
            val multiplyStatements = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex()
                .findAll(rawData.joinToString("")).toList()
                .map { it.groups.first()?.value }

            return multiplyStatements
                .map { it?.removePrefix("mul(")?.removeSuffix(")")?.split(',')?.map { it.toInt() } }
                .map { Pair(it?.first()!!, it.last()) }
        }
    }
}
