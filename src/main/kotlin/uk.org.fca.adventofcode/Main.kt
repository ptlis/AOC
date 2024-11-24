package uk.org.fca.adventofcode

import uk.org.fca.adventofcode.y2023.day1.Day1
import uk.org.fca.adventofcode.y2023.day10.Day10
import uk.org.fca.adventofcode.y2023.day11.Day11
import uk.org.fca.adventofcode.y2023.day12.Day12
import uk.org.fca.adventofcode.y2023.day2.Day2
import uk.org.fca.adventofcode.y2023.day3.Day3
import uk.org.fca.adventofcode.y2023.day4.Day4
import uk.org.fca.adventofcode.y2023.day5.Day5
import uk.org.fca.adventofcode.y2023.day6.Day6
import uk.org.fca.adventofcode.y2023.day7.Day7
import uk.org.fca.adventofcode.y2023.day8.Day8
import uk.org.fca.adventofcode.y2023.day9.Day9
import java.math.BigInteger
import kotlin.time.measureTime

fun main () {
    runDays(listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5(),
        Day6(),
        Day7(),
        Day8(),
        Day9(),
        Day10(),
        Day11(),
        Day12(),
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
