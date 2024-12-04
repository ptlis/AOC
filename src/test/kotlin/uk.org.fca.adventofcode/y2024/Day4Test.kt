package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2024.day4.Day4

class Day4Test: FunSpec({
    val example1Data = """
        ..X...
        .SAMX.
        .A..A.
        XMAS.S
        .X....
    """.trimIndent().lines()

    context("Part 1") {
        test("Find XMAS count in string") {
            Day4.findVerticalXmases("..XMAS...XMASAMX.." ) shouldBeEqual 3
        }

        test("Solve with example 1 data") {
            Day4.solvePart1(example1Data) shouldBeEqual 4
        }
    }
})
