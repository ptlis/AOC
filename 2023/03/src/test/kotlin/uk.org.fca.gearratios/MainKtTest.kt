package uk.org.fca.gearratios

import kotlin.test.assertEquals
import kotlin.test.Test

class MainKtTest {
    val testEngine = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
""".trimIndent().lines().map { it.toCharArray() }

    @Test
    fun testGetPossiblePartNumber() {
        val input = "....7512.."
        val expected = "7512"

        assertEquals(expected, getPossiblePartNumber(input.toCharArray(), 4))
    }

    @Test
    fun testIsPartNumber1() {
        val expected = true

        assertEquals(expected, isPartNumber(testEngine, 0, 0, "467"))
    }

    @Test
    fun testIsPartNumber2() {
        val expected = false

        assertEquals(expected, isPartNumber(testEngine, 5, 0, "114"))
    }

    @Test
    fun testIsPartNumber3() {
        val expected = false

        assertEquals(expected, isPartNumber(testEngine, 7, 5, "58"))
    }

    @Test
    fun testIsPartNumber4() {
        val expected = true

        assertEquals(expected, isPartNumber(testEngine, 2, 6, "592"))
    }

    @Test
    fun testFindPartNumbers() {
        val expected = listOf(467, 35, 633, 617, 592, 755, 664, 598)

        assertEquals(expected, findPartNumbers(testEngine))
    }
}
