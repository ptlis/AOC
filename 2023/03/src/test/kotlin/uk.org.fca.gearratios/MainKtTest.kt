package uk.org.fca.gearratios

import java.io.File
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
    fun testSolution() {
        val engine = File("data/engine")
            .useLines { it.toList() }
            .map { it.toCharArray() }

        assertEquals(553079, findPartNumbers(engine).sumOf { it.number.toInt() })
    }
}
