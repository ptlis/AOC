package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2024.day6.Day6

class Day6Test : FunSpec ({
    val example1Data = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent().lines()

    context("Part 1") {
        test("Test solvePart1") {
            Day6.solvePart1(example1Data) shouldBeEqual 41
        }
    }
})
