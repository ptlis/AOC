package uk.org.fca.adventofcode

import java.io.File
import java.math.BigInteger

abstract class Day {
    abstract fun part1Solution(data: List<String>): BigInteger
    abstract fun part2Solution(data: List<String>): BigInteger
    abstract val number: Int

    fun getDayData(): List<String> {
        return File("src/main/resources/2023/day$number").readLines()
    }

    override fun toString(): String {
        return "day $number"
    }
}
