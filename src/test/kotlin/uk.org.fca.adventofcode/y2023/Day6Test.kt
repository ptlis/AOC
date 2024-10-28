package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.y2023.day6.RaceResult
import uk.org.fca.adventofcode.y2023.day6.RaceResults
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    private val testRaceResultData = """
        Time:      7  15   30
        Distance:  9  40  200""".trimIndent().lines()

    @Test
    fun testPart1ExampleRace1WinningStrategies() {
        val input = RaceResult(BigInteger.valueOf(7), BigInteger.valueOf(9))
        val expected = BigInteger.valueOf(4)

        assertEquals(expected, input.winningStrategyCount)
    }

    @Test
    fun testPart1ExampleRace2WinningStrategies() {
        val input = RaceResult(BigInteger.valueOf(15), BigInteger.valueOf(40))
        val expected = BigInteger.valueOf(8)

        assertEquals(expected, input.winningStrategyCount)
    }

    @Test
    fun testPart1ExampleRace3WinningStrategies() {
        val input = RaceResult(BigInteger.valueOf(30), BigInteger.valueOf(200))
        val expected = BigInteger.valueOf(9)

        assertEquals(expected, input.winningStrategyCount)
    }

    @Test
    fun testPart1ExampleMarginOfError() {
        val expected = BigInteger.valueOf(288)

        assertEquals(expected, RaceResults.parse(testRaceResultData).marginOfError)
    }

    @Test
    fun testPart2ExampleParse() {
        val expected = RaceResult(BigInteger.valueOf(71530), BigInteger.valueOf(940200))

        assertEquals(expected, RaceResult.parse(testRaceResultData))
    }

    @Test
    fun testPart2ExampleWinningStrategyCount() {
        val input = RaceResult(BigInteger.valueOf(71530), BigInteger.valueOf(940200))
        val expected = BigInteger.valueOf(71503)

        assertEquals(expected, input.winningStrategyCount)
    }
}
