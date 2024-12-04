package uk.org.fca.adventofcode

import uk.org.fca.adventofcode.y2023.day1.Day1 as Day1Year2023
import uk.org.fca.adventofcode.y2023.day2.Day2 as Day2Year2023
import uk.org.fca.adventofcode.y2023.day3.Day3 as Day3Year2023
import uk.org.fca.adventofcode.y2023.day4.Day4 as Day4Year2023
import uk.org.fca.adventofcode.y2023.day5.Day5 as Day5Year2023
import uk.org.fca.adventofcode.y2023.day6.Day6 as Day6Year2023
import uk.org.fca.adventofcode.y2023.day7.Day7 as Day7Year2023
import uk.org.fca.adventofcode.y2023.day8.Day8 as Day8Year2023
import uk.org.fca.adventofcode.y2023.day9.Day9 as Day9Year2023
import uk.org.fca.adventofcode.y2023.day10.Day10 as Day10Year2023
import uk.org.fca.adventofcode.y2023.day11.Day11 as Day11Year2023
import uk.org.fca.adventofcode.y2023.day12.Day12 as Day12Year2023
import uk.org.fca.adventofcode.y2024.day1.Day1 as Day1Year2024
import uk.org.fca.adventofcode.y2024.day2.Day2 as Day2Year2024
import uk.org.fca.adventofcode.y2024.day3.Day3 as Day3Year2024
import uk.org.fca.adventofcode.y2024.day4.Day4 as Day4Year2024
import java.math.BigInteger
import kotlin.time.measureTime

fun main () {
    runDays(listOf(
        // 2023
        Day1Year2023(),
        Day2Year2023(),
        Day3Year2023(),
        Day4Year2023(),
        Day5Year2023(),
        Day6Year2023(),
        Day7Year2023(),
        Day8Year2023(),
        Day9Year2023(),
        Day10Year2023(),
        Day11Year2023(),
        Day12Year2023(),

        // 2024
        Day1Year2024(),
        Day2Year2024(),
        Day3Year2024(),
        Day4Year2024(),
    ).associateBy { it.day })
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
