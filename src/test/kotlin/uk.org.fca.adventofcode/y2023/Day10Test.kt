package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
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
})
