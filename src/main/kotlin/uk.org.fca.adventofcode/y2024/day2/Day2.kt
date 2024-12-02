package uk.org.fca.adventofcode.y2024.day2

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import kotlin.math.abs

typealias UnusualData = List<List<Int>>

class Day2: Day() {
    override fun part1Solution(): BigInteger {
        return BigInteger.valueOf(solvePart1(parse(dayData)).toLong())
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(solvePart2(parse(dayData)).toLong())
    }

    override val year: Int get() = 2024
    override val day: Int get() = 2

    companion object {
        fun parse(rawData: List<String>): UnusualData = rawData.map { it.split(" ").map { it.toInt() } }

        fun solvePart1(locations: UnusualData): Int =
            locations
                .filter { isLevelValid(it) }.size

        fun isLevelValid(levels: List<Int>): Boolean {
            val levelDiffs = levels.zipWithNext().map { it.first - it.second }
            val greaterThanZero = levelDiffs.filter { it >= 0 }
            return (greaterThanZero.size == levelDiffs.size || greaterThanZero.isEmpty())
                && (levelDiffs.map { abs(it) }.none { it < 1 || it > 3 })
        }

        fun solvePart2(locations: UnusualData): Int =
            locations
                .map { getValidVariants(it) }
                .filter {
                    !it.none { isLevelValid(it) }
                }.size

        fun getValidVariants(levels: List<Int>): List<List<Int>> {
            val variants = mutableListOf(levels)
            for (i in levels.indices) {
                val variantList = levels.toMutableList()
                variantList.removeAt(i)
                variants.add(variantList.toList())
            }
            return variants
        }
    }
}
