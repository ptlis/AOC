package uk.org.fca.adventofcode

import uk.org.fca.adventofcode.`2023`.*
import java.math.BigInteger
import kotlin.time.measureTime

fun main () {
    runDays(mapOf(
        1 to Day1(),
        2 to Day2(),
        3 to Day3(),
        4 to Day4(),
        5 to Day5(),
        6 to Day6()
    ))
}

fun runDays(days: Map<Int, Day>) {
    for (entry in days.entries) {
        runDay(entry.key, entry.value)
    }
}

fun runDay(dayNumber: Int, day: Day) {
    val dayData = object {}.javaClass.getResourceAsStream("/2023/day$dayNumber")?.bufferedReader()?.readLines() ?: listOf()

    var part1Solution: BigInteger
    val part1ExecutionTime = measureTime{
        part1Solution = day.part1Solution(dayData)
    }

    var part2Solution: BigInteger
    val part2ExecutionTime = measureTime{
        part2Solution = day.part2Solution(dayData)
    }

    println("Day $dayNumber Part 1: ${part1Solution.toString().padEnd(15)} ($part1ExecutionTime)")
    println("Day $dayNumber Part 2: ${part2Solution.toString().padEnd(15)} ($part2ExecutionTime)")
}