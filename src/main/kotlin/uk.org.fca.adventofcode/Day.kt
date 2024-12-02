package uk.org.fca.adventofcode

import java.io.File
import java.math.BigInteger

abstract class Day {
    abstract fun part1Solution(): BigInteger
    abstract fun part2Solution(): BigInteger
    abstract val day: Int
    abstract val year: Int

    val dayData get() = rawDayData.lines().filter { it.isNotEmpty() }

    val rawDayData get() = File("src/main/resources/$year/day$day").readText()

    override fun toString(): String {
        return "$year, day $day"
    }
}
