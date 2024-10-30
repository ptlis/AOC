package uk.org.fca.adventofcode

import java.io.File
import java.math.BigInteger

abstract class Day {
    abstract fun part1Solution(): BigInteger
    abstract fun part2Solution(): BigInteger
    abstract val number: Int

    val dayData get() = File("src/main/resources/2023/day$number").readLines()

    override fun toString(): String {
        return "day $number"
    }
}
