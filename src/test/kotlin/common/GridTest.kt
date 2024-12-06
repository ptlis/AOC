package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.Coord
import uk.org.fca.adventofcode.common.Grid

class GridTest : FunSpec({
    test("parseCharGrid") {
        val data = """
            abc
            def
            hij
        """.trimIndent().lines()

        Grid.parseCharGrid(data) shouldBeEqual Grid(listOf(
            listOf('a', 'b', 'c'),
            listOf('d', 'e', 'f'),
            listOf('h', 'i', 'j'),
        ))
    }

    test("parseIntGrid") {
        val data = """
            123
            456
            789
        """.trimIndent().lines()

        Grid.parseIntGrid(data) shouldBeEqual Grid(listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
        ))
    }

    test("findValue") {
        Grid(listOf(
            listOf('a', 'b', '#'),
            listOf('#', 'e', 'f'),
            listOf('h', 'i', 'j'),
        )).findValue('#') shouldBeEqual listOf(Coord(2, 0), Coord(0, 1))
    }

    context("exists") {
        test("coord is in grid") {
            Grid(listOf(
                listOf('a', 'b', '#'),
                listOf('#', 'e', 'f'),
                listOf('h', 'i', 'j'),
            )).exists(Coord(1, 1)) shouldBeEqual true
        }

        test("coord is not in grid") {
            Grid(listOf(
                listOf('a', 'b', '#'),
                listOf('#', 'e', 'f'),
                listOf('h', 'i', 'j'),
            )).exists(Coord(4, 4)) shouldBeEqual false
        }
    }

    context("isOnEdge") {
        test("coord is on edge") {
            Grid(listOf(
                listOf('a', 'b', '#'),
                listOf('#', 'e', 'f'),
                listOf('h', 'i', 'j'),
            )).isOnEdge(Coord(0, 1)) shouldBeEqual true
        }

        test("coord is not on edge") {
            Grid(listOf(
                listOf('a', 'b', '#'),
                listOf('#', 'e', 'f'),
                listOf('h', 'i', 'j'),
            )).isOnEdge(Coord(1, 1)) shouldBeEqual false
        }
    }
})
