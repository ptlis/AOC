package uk.org.fca.adventofcode.`2023`

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    private val testRaceResults = RaceResults.parse("""
        Time:      7  15   30
        Distance:  9  40  200""".trimIndent().lines())

    @Test
    fun testPart1ExampleRace1WinningStrategies() {
        val input = RaceResult(7, 9)
        val expected = listOf(2, 3, 4, 5)

        assertEquals(expected, input.winningStrategies)
    }

    @Test
    fun testPart1ExampleRace2WinningStrategies() {
        val input = RaceResult(15, 40)
        val expected = listOf(4, 5, 6, 7, 8, 9, 10, 11)

        assertEquals(expected, input.winningStrategies)
    }

    @Test
    fun testPart1ExampleRace3WinningStrategies() {
        val input = RaceResult(30, 200)
        val expected = listOf(11, 12, 13, 14, 15, 16, 17, 18, 19)

        assertEquals(expected, input.winningStrategies)
    }

    @Test
    fun testPart1ExampleMarginOfError() {
        val expected = 288

        assertEquals(expected, testRaceResults.marginOfError)
    }
}