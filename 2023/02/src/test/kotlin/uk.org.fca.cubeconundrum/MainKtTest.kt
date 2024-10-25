package uk.org.fca.cubeconundrum

import kotlin.test.assertEquals
import kotlin.test.Test

class MainKtTest {
    @Test
    fun testParseColourCount() {
        val input = "7 blue"
        val expected = Pair(Color.BLUE, 7)

        assertEquals(expected, parseColorCount(input))
    }

    @Test
    fun testParseDrawColours() {
        val input = "3 green, 2 blue, 5 red"
        val expected = listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 2), Pair(Color.RED, 5))

        assertEquals(expected, parseDrawColors(input))
    }

    @Test
    fun testParseDraws() {
        val input = "3 green, 2 blue, 5 red; 2 red, 3 blue; 1 green"
        val expected = listOf(
            listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 2), Pair(Color.RED, 5)),
            listOf(Pair(Color.RED, 2), Pair(Color.BLUE, 3)),
            listOf(Pair(Color.GREEN, 1))
        )

        assertEquals(expected, parseDraws(input))
    }

    @Test
    fun testParseExample1Game1() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val expected = Pair(1, listOf(
            listOf(Pair(Color.BLUE, 3), Pair(Color.RED, 4)),
            listOf(Pair(Color.RED, 1), Pair(Color.GREEN, 2), Pair(Color.BLUE, 6)),
            listOf(Pair(Color.GREEN, 2))
        ))

        assertEquals(expected, parseGameData(input))
    }

    @Test
    fun testIsDrawPossible1() {
        val input = listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 2), Pair(Color.RED, 5))
        val expected = true

        assertEquals(expected, isDrawPossible(input))
    }

    @Test
    fun testIsDrawPossible2() {
        val input = listOf(Pair(Color.BLUE, 5), Pair(Color.GREEN, 20), Pair(Color.GREEN, 9))
        val expected = false

        assertEquals(expected, isDrawPossible(input))
    }

    @Test
    fun testAreDrawsPossible1() {
        val input = listOf(
            listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 2), Pair(Color.RED, 5)),
            listOf(Pair(Color.RED, 2), Pair(Color.BLUE, 3)),
            listOf(Pair(Color.GREEN, 1))
        )
        val expected = true

        assertEquals(expected, areDrawsPossible(input))
    }

    @Test
    fun testAreDrawsPossible2() {
        val input = listOf(
            listOf(Pair(Color.RED, 2), Pair(Color.BLUE, 3)),
            listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 30), Pair(Color.RED, 5)),
            listOf(Pair(Color.GREEN, 1))
        )
        val expected = false

        assertEquals(expected, areDrawsPossible(input))
    }

    @Test
    fun testIsGamePossible1() {
        val input = Pair(
            11,
            listOf(
                listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 3), Pair(Color.RED, 5)),
                listOf(Pair(Color.RED, 2), Pair(Color.BLUE, 3)),
                listOf(Pair(Color.GREEN, 1))
            )
        )
        val expected = true

        assertEquals(expected, isGamePossible(input))
    }

    @Test
    fun testIsGamePossible2() {
        val input = Pair(
            11,
            listOf(
                listOf(Pair(Color.GREEN, 3), Pair(Color.BLUE, 30), Pair(Color.RED, 5)),
                listOf(Pair(Color.RED, 2), Pair(Color.BLUE, 99)),
                listOf(Pair(Color.GREEN, 1))
            )
        )
        val expected = false

        assertEquals(expected, isGamePossible(input))
    }

    @Test
    fun testOneGameExample1Line1() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val expected = true

        assertEquals(expected, isGamePossible(parseGameData(input)))
    }

    @Test
    fun testOneGameExample1Line2() {
        val input = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
        val expected = true

        assertEquals(expected, isGamePossible(parseGameData(input)))
    }

    @Test
    fun testOneGameExample1Line3() {
        val input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
        val expected = false

        assertEquals(expected, isGamePossible(parseGameData(input)))
    }

    @Test
    fun testOneGameExample1Line4() {
        val input = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
        val expected = false

        assertEquals(expected, isGamePossible(parseGameData(input)))
    }

    @Test
    fun testOneGameExample1Line5() {
        val input = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        val expected = true

        assertEquals(expected, isGamePossible(parseGameData(input)))
    }
}
