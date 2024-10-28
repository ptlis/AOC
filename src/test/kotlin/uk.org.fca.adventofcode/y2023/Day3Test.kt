package uk.org.fca.adventofcode.y2023

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    private val testEngine = """
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
    fun testFindPartNumbers() {
        val expected = listOf(
            PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(6, 2), 633, Symbol(Coord(6, 3), '#')),
            PartNumber(Coord(0, 4), 617, Symbol(Coord(3, 4), '*')),
            PartNumber(Coord(2, 6), 592, Symbol(Coord(5, 5), '+')),
            PartNumber(Coord(1, 9), 664, Symbol(Coord(3, 8), '$')),
            PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
            PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
        )

        assertEquals(expected, findPartNumbers(testEngine))
    }

    @Test
    fun testExamplePart1Sum() {
        val expected = 4361

        assertEquals(expected, findPartNumbers(testEngine).sumOf { it.number })
    }

    @Test
    fun findCogs() {
        val expected = listOf(
            Cog(listOf(
                PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
            )),
            Cog(listOf(
                PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
                PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
            ))
        )

        assertEquals(expected, findCogs(listOf(
            PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(6, 2), 633, Symbol(Coord(6, 3), '#')),
            PartNumber(Coord(0, 4), 617, Symbol(Coord(3, 4), '*')),
            PartNumber(Coord(2, 6), 592, Symbol(Coord(5, 5), '+')),
            PartNumber(Coord(1, 9), 664, Symbol(Coord(3, 8), '$')),
            PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
            PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
        )))
    }

    @Test
    fun testCogRatio() {
        val input = Cog(listOf(
            PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
        ))
        val expected = 16345

        assertEquals(expected, input.ratio)
    }

    @Test
    fun testExamplePart2Sum() {
        val expected = 467835

        assertEquals(expected, findCogs(listOf(
            PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
            PartNumber(Coord(6, 2), 633, Symbol(Coord(6, 3), '#')),
            PartNumber(Coord(0, 4), 617, Symbol(Coord(3, 4), '*')),
            PartNumber(Coord(2, 6), 592, Symbol(Coord(5, 5), '+')),
            PartNumber(Coord(1, 9), 664, Symbol(Coord(3, 8), '$')),
            PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
            PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
        )).sumOf { it.ratio })
    }
}
