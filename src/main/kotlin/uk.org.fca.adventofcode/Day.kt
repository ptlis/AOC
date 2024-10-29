package uk.org.fca.adventofcode

import java.io.File
import java.math.BigInteger

interface Day {
    fun part1Solution(data: List<String>): BigInteger
    fun part2Solution(data: List<String>): BigInteger
    val number: Int

    fun getDayData(): List<String> {
        return File("src/main/resources/2023/day$number").readLines() ?: listOf()
    }
}
