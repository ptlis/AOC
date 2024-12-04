package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.Coord
import uk.org.fca.adventofcode.y2024.day4.Day4

class Day4Test: FunSpec({
    val example1Data = """
        ..X...
        .SAMX.
        .A..A.
        XMAS.S
        .X....
    """.trimIndent().lines()

    val example2Data = """
        ....XXMAS.
        .SAMXMS...
        ...S..A...
        ..A.A.MS.X
        XMASAMX.MM
        X.....XA.A
        S.S.S.S.SS
        .A.A.A.A.A
        ..M.M.M.MM
        .X.X.XMASX
    """.trimIndent().lines()

    context("Part 1") {
        test("Find XMAS count in string") {
            Day4.findVerticalXmases("..XMAS...XMASAMX.." ) shouldBeEqual 3
        }

        test("Solve with example 1 data") {
            Day4.solvePart1(example1Data) shouldBeEqual 4
        }

        test("Solve with example 2 data") {
            Day4.solvePart1(example2Data) shouldBeEqual 18
        }
    }

    val example3Data = """
        M.S
        .A.
        M.S
    """.trimIndent().lines()

    val example4Data = """
        .M.S......
        ..A..MSMS.
        .M.S.MAA..
        ..A.ASMSM.
        .M.S.M....
        ..........
        S.S.S.S.S.
        .A.A.A.A..
        M.M.M.M.M.
        ..........
    """.trimIndent().lines()

    context("Part 2") {
        test("Find centre candidates example 3") {
            Day4.findCentreCandidates(example3Data) shouldBeEqual listOf(Coord(1, 1))
        }

        test("Find centre candidates example 4") {
            Day4.findCentreCandidates(example4Data) shouldBeEqual listOf(
                Coord(2, 1),
                Coord(6, 2),
                Coord(7, 2),
                Coord(2, 3),
                Coord(4, 3),
                Coord(1, 7),
                Coord(3, 7),
                Coord(5, 7),
                Coord(7, 7),
            )
        }

        test("Solve for example 3") {
            Day4.solvePart2(example3Data) shouldBeEqual 1
        }

        test("Solve for example 4") {
            Day4.solvePart2(example4Data) shouldBeEqual 9
        }
    }
})
