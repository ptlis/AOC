package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class CompletedSolutionsTest {
    data class CompletedSolution(
        val day: Day,
        val part1Solution: BigInteger,
        val part2Solution: BigInteger
    )

    private val solutions = listOf(
        CompletedSolution(Day1(), BigInteger.valueOf(53386), BigInteger.valueOf(53312)),
        CompletedSolution(Day2(), BigInteger.valueOf(2449), BigInteger.valueOf(63981)),
        CompletedSolution(Day3(), BigInteger.valueOf(553079), BigInteger.valueOf(84363105)),
        CompletedSolution(Day4(), BigInteger.valueOf(21158), BigInteger.valueOf(6050769)),
        CompletedSolution(Day5(), BigInteger.valueOf(825516882), BigInteger.valueOf(-1)),
        CompletedSolution(Day6(), BigInteger.valueOf(128700), BigInteger.valueOf(39594072)),
        CompletedSolution(Day7(), BigInteger.valueOf(253910319), BigInteger.valueOf(-1))
    )

    @Test
    fun testCompletedSolutions() {
        solutions.forEach {
            assertEquals(it.part1Solution, it.day.part1Solution(it.day.getDayData()), "Failure in day ${it.day.number} part 1")
            assertEquals(it.part2Solution, it.day.part2Solution(it.day.getDayData()), "Failure in day ${it.day.number} part 2")
        }
    }
}
