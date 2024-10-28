package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class CompletedSolutionsTest {
    data class CompletedSolution(
        val day: Day,
        val part1Solution: BigInteger,
        val part2Solution: BigInteger,
        val data: List<String>
    )

    private val solutions = listOf(
        CompletedSolution(Day1(), BigInteger.valueOf(53386), BigInteger.valueOf(53312), getDayData(1)),
        CompletedSolution(Day2(), BigInteger.valueOf(2449), BigInteger.valueOf(63981), getDayData(2)),
        CompletedSolution(Day3(), BigInteger.valueOf(553079), BigInteger.valueOf(84363105), getDayData(3)),
        CompletedSolution(Day4(), BigInteger.valueOf(21158), BigInteger.valueOf(6050769), getDayData(4)),
        CompletedSolution(Day5(), BigInteger.valueOf(825516882), BigInteger.valueOf(-1), getDayData(5)),
        CompletedSolution(Day6(), BigInteger.valueOf(128700), BigInteger.valueOf(39594072), getDayData(6))
    )

    companion object {
        fun getDayData(dayNumber: Int): List<String> {
            return object {}.javaClass.getResourceAsStream("/2023/day$dayNumber")?.bufferedReader()?.readLines() ?: listOf()
        }
    }

    @Test
    fun testCompletedSolutions() {
        solutions.forEach {
            assertEquals(it.part1Solution, it.day.part1Solution(it.data))
            assertEquals(it.part2Solution, it.day.part2Solution(it.data))
        }
    }
}
