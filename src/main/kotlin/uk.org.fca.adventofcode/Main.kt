package uk.org.fca.adventofcode

import uk.org.fca.adventofcode.y2023.*
import java.math.BigInteger
import kotlin.time.measureTime

fun main () {
    runDays(listOf(
//        Day1(),
//        Day2(),
//        Day3(),
//        Day4(),
//        Day5(),
//        Day6(),
//        Day7(),
        Day8()
    ).associateBy { it.number })
}

fun runDays(days: Map<Int, Day>) {
    val totalExecutionTime = measureTime {
        for (entry in days.entries) {
            runDay(entry.key, entry.value)
        }
    }

    println("")
    println("Total execution time: $totalExecutionTime")
}

fun runDay(dayNumber: Int, day: Day) {
    var part1Solution: BigInteger
    val part1ExecutionTime = measureTime{
        part1Solution = day.part1Solution()
    }

    var part2Solution: BigInteger
    val part2ExecutionTime = measureTime{
        part2Solution = day.part2Solution()
    }

    println("Day $dayNumber Part 1: ${part1Solution.toString().padEnd(15)} ($part1ExecutionTime)")
    println("Day $dayNumber Part 2: ${part2Solution.toString().padEnd(15)} ($part2ExecutionTime)")
}
