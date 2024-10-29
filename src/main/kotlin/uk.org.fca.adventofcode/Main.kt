package uk.org.fca.adventofcode

import uk.org.fca.adventofcode.y2023.*
import java.io.File
import java.math.BigInteger
import kotlin.time.measureTime

fun main () {
    runDays(mapOf(
        1 to Day1(),
        2 to Day2(),
        3 to Day3(),
        4 to Day4(),
        5 to Day5(),
        6 to Day6(),
        7 to Day7()
    ))
}

fun runDays(days: Map<Int, Day>) {
    for (entry in days.entries) {
        runDay(entry.key, entry.value)
    }
}

fun runDay(dayNumber: Int, day: Day) {
    var part1Solution: BigInteger
    val part1ExecutionTime = measureTime{
        part1Solution = day.part1Solution(day.getDayData())
    }

    var part2Solution: BigInteger
    val part2ExecutionTime = measureTime{
        part2Solution = day.part2Solution(day.getDayData())
    }

    println("Day $dayNumber Part 1: ${part1Solution.toString().padEnd(15)} ($part1ExecutionTime)")
    println("Day $dayNumber Part 2: ${part2Solution.toString().padEnd(15)} ($part2ExecutionTime)")
}
