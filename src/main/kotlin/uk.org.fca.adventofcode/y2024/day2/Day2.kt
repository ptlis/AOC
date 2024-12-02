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
        return BigInteger.valueOf(-1)
    }

    override val year: Int get() = 2024
    override val day: Int get() = 2

    companion object {
        fun parse(rawData: List<String>): UnusualData = rawData.map { it.split(" ").map { it.toInt() } }

        fun solvePart1(locations: UnusualData): Int =
            locations
                .map { levels -> levels.zipWithNext().map { it.first - it.second } }
                .filter { levelsDiff ->
                    val greaterThanZero = levelsDiff.filter { it >= 0 }
                    greaterThanZero.size == levelsDiff.size || greaterThanZero.isEmpty()
                }
                .filter { levelsDiff ->
                    levelsDiff.map { abs(it) }.none { it < 1 || it > 3 }
                }
                .size
    }
}