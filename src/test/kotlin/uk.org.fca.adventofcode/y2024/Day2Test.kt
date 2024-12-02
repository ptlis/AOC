package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2024.day2.Day2

class Day2Test: FunSpec({
    val example1Data = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().lines()

    context("Part 1") {
        test("example 1 solution") {
            Day2.solvePart1(Day2.parse(example1Data)) shouldBeEqual 2
        }
    }

    context("Part 2") {
        test("example 1 solution") {
            Day2.solvePart2(Day2.parse(example1Data)) shouldBeEqual 4
        }
    }
})
