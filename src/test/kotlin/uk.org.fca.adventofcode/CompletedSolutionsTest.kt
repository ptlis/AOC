package uk.org.fca.adventofcode

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
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
import java.math.BigInteger

class CompletedSolutionsTest: FunSpec({
    data class CompletedSolution(
        val day: Day,
        val part1Solution: BigInteger,
        val part2Solution: BigInteger
    ) {
        override fun toString(): String = day.toString()
    }

    val completedSolutions = listOf(
        // 2023
        CompletedSolution(Day1Year2023(), BigInteger.valueOf(53386), BigInteger.valueOf(53312)),
        CompletedSolution(Day2Year2023(), BigInteger.valueOf(2449), BigInteger.valueOf(63981)),
        CompletedSolution(Day3Year2023(), BigInteger.valueOf(553079), BigInteger.valueOf(84363105)),
        CompletedSolution(Day4Year2023(), BigInteger.valueOf(21158), BigInteger.valueOf(6050769)),
        CompletedSolution(Day5Year2023(), BigInteger.valueOf(825516882), BigInteger.valueOf(-1)),
        CompletedSolution(Day6Year2023(), BigInteger.valueOf(128700), BigInteger.valueOf(39594072)),
        CompletedSolution(Day7Year2023(), BigInteger.valueOf(253910319), BigInteger.valueOf(-1)),
        CompletedSolution(Day8Year2023(), BigInteger.valueOf(12643), BigInteger.valueOf(-1)),
        CompletedSolution(Day9Year2023(), BigInteger.valueOf(1684566095), BigInteger.valueOf(1136)),
        CompletedSolution(Day10Year2023(), BigInteger.valueOf(6778), BigInteger.valueOf(433)),
        CompletedSolution(Day11Year2023(), BigInteger.valueOf(9724940), BigInteger.valueOf(569052586852)),
        CompletedSolution(Day12Year2023(), BigInteger.valueOf(8419), BigInteger.valueOf(-1)),

        // 2024
        CompletedSolution(Day1Year2024(), BigInteger.valueOf(2285373), BigInteger.valueOf(21142653)),
        CompletedSolution(Day2Year2024(), BigInteger.valueOf(479), BigInteger.valueOf(531)),
        CompletedSolution(Day3Year2024(), BigInteger.valueOf(175615763), BigInteger.valueOf(74361272)),
    )

    context("Test completed solutions (part 1)") {
        withData(completedSolutions.associateBy { "$it part 1" }) {
            completedSolution -> completedSolution.day.part1Solution() shouldBeEqual completedSolution.part1Solution
        }
    }

    context("Test completed solutions (part 2)") {
        withData(completedSolutions.associateBy { "$it part 2" }) {
            completedSolution -> completedSolution.day.part2Solution() shouldBeEqual completedSolution.part2Solution
        }
    }
})
