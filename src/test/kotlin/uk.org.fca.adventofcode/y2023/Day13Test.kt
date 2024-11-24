package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day13.Pattern

class Day13Test: FunSpec({
    context("Part 1") {
        test("horizontalMirror (found)") {
            Pattern("""
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.""".trimIndent().lines()
            ).horizontalMirror shouldBeEqual 5
        }

        test("horizontalMirror (not found)") {
            Pattern("""
                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#""".trimIndent().lines()
            ).horizontalMirror shouldBeEqual -1
        }

        test("Rotate") {
            Pattern("""
                12345
                abcde
                67890
                fghij
            """.trimIndent().lines()).rotated() shouldBeEqual listOf(
                "1a6f",
                "2b7g",
                "3c8h",
                "4d9i",
                "5e0j"
            )
        }

        test("verticalMirror (found)") {
            Pattern("""
                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#""".trimIndent().lines()
            ).verticalMirror shouldBeEqual 400
        }

        test("verticalMirror (not found)") {
            Pattern("""
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.""".trimIndent().lines()
            ).verticalMirror shouldBeEqual -1
        }

        test("parse") {
            Pattern.parse("""
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.

                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#
            """.trimIndent()).size shouldBeEqual 2
        }

        test("summarization pattern 1") {
            Pattern("""
                #.##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.#.##.#.""".trimIndent().lines()
            ).summarization shouldBeEqual 5
        }

        test("summarization pattern 2") {
            Pattern("""
                #...##..#
                #....#..#
                ..##..###
                #####.##.
                #####.##.
                ..##..###
                #....#..#""".trimIndent().lines()
            ).summarization shouldBeEqual 400
        }

        test("summarization pattern 3") {
            Pattern("""
                #...##..#
                #....#..#
                ..##..###
                123456789
                #####.##.
                ..##..###
                #....#..#""".trimIndent().lines()
            ).summarization shouldBeEqual 0
        }
    }
})