package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2024.day1.Day1

class Day1Test: FunSpec({
    val example1Data = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent().lines()

    context("Part 1") {
        test("parse") {
            Day1.parse(example1Data) shouldBeEqual Pair(
                listOf(3, 4, 2, 1, 3, 3),
                listOf(4, 3, 5, 3, 9, 3),
            )
        }

        test("solve part 1") {
            Day1.solvePart1(Day1.parse(example1Data)) shouldBeEqual 11
        }
    }
})