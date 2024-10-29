package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.Day
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
        CompletedSolution(Day1(), BigInteger.valueOf(53386), BigInteger.valueOf(53312)),
        CompletedSolution(Day2(), BigInteger.valueOf(2449), BigInteger.valueOf(63981)),
        CompletedSolution(Day3(), BigInteger.valueOf(553079), BigInteger.valueOf(84363105)),
        CompletedSolution(Day4(), BigInteger.valueOf(21158), BigInteger.valueOf(6050769)),
        CompletedSolution(Day5(), BigInteger.valueOf(825516882), BigInteger.valueOf(-1)),
        CompletedSolution(Day6(), BigInteger.valueOf(128700), BigInteger.valueOf(39594072)),
        CompletedSolution(Day7(), BigInteger.valueOf(253910319), BigInteger.valueOf(-1))
    )

    context("Test completed solutions (part 1)") {
        withData(completedSolutions.associateBy { "$it part 1" }) {
            completedSolution -> completedSolution.day.part1Solution(completedSolution.day.getDayData()) shouldBeEqual completedSolution.part1Solution
        }
    }

    context("Test completed solutions (part 2)") {
        withData(completedSolutions.associateBy { "$it part 2" }) {
                completedSolution -> completedSolution.day.part2Solution(completedSolution.day.getDayData()) shouldBeEqual completedSolution.part2Solution
        }
    }
})
