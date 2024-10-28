package uk.org.fca.adventofcode.y2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun testFullConfigurationExample1() {
        val input = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
        )

        val expected = 142
        assertEquals(expected, calibrate(input, false))
    }

    @Test
    fun testFullConfigurationExample2() {
        val input = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
        )

        val expected = 281
        assertEquals(expected, calibrate(input, true))
    }

    @Test
    fun testOverlappingDigits() {
        val input = "eighthree"
        val expected = 83
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample1Line1() {
        val input = "1abc2"
        val expected = 12
        assertEquals(expected, getDigits(input, false))
    }

    @Test
    fun testOneLineConfigurationExample1Line2() {
        val input = "pqr3stu8vwx"
        val expected = 38
        assertEquals(expected, getDigits(input, false))
    }

    @Test
    fun testOneLineConfigurationExample1Line3() {
        val input = "a1b2c3d4e5f"
        val expected = 15
        assertEquals(expected, getDigits(input, false))
    }

    @Test
    fun testOneLineConfigurationExample1Line4() {
        val input = "treb7uchet"
        val expected = 77
        assertEquals(expected, getDigits(input, false))
    }

    @Test
    fun testOneLineConfigurationExample2Line1() {
        val input = "two1nine"
        val expected = 29
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample2Line2() {
        val input = "eightwothree"
        val expected = 83
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample2Line3() {
        val input = "abcone2threexyz"
        val expected = 13
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample2Line4() {
        val input = "xtwone3four"
        val expected = 24
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample2Line5() {
        val input = "4nineeightseven2"
        val expected = 42
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample2Line6() {
        val input = "zoneight234"
        val expected = 14
        assertEquals(expected, getDigits(input, true))
    }

    @Test
    fun testOneLineConfigurationExample2Line7() {
        val input = "7pqrstsixteen"
        val expected = 76
        assertEquals(expected, getDigits(input, true))
    }
}
