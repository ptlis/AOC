package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.Coord
import uk.org.fca.adventofcode.y2023.day10.Maze

class Day10Test: FunSpec({
    context("Part 1") {
        test("Example 1") {
            val maze = Maze.parse("""
                .....
                .S-7.
                .|.|.
                .L-J.
                .....
            """.trimIndent().lines())

            maze.stepsFromStartToFurthestPoint shouldBeEqual 4
        }

        test("Example 2") {
            val maze = Maze.parse("""
                ..F7.
                .FJ|.
                SJ.L7
                |F--J
                LJ...
            """.trimIndent().lines())

            maze.stepsFromStartToFurthestPoint shouldBeEqual 8
        }
    }

    context("Part 2") {
        test("Example 1") {
            val maze = Maze.parse("""
                .....
                .S-7.
                .|.|.
                .L-J.
                .....
            """.trimIndent().lines())

            maze.insideCoords shouldBeEqual listOf(Coord(2, 2))
        }

        test("Example 2") {
            val maze = Maze.parse("""
                FF7FSF7F7F7F7F7F---7
                L|LJ||||||||||||F--J
                FL-7LJLJ||||||LJL-77
                F--JF--7||LJLJ7F7FJ-
                L---JF-JLJ.||-FJLJJ7
                |F|F-JF---7F7-L7L|7|
                |FFJF7L7F-JF7|JL---7
                7-L-JL7||F7|L7F-7F7|
                L.L7LFJ|||||FJL7||LJ
                L7JLJL-JLJLJL--JLJ.L
            """.trimIndent().lines())

            maze.insideCoords.size shouldBeEqual 10
        }
    }
})
