package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2024.day5.Day5

class Day5Test: FunSpec({
    val example1Data = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent().lines()

    context("Part 1") {
        test("Solve with example data") {
            Day5.solvePart1(example1Data) shouldBeEqual 143
        }
    }

    context("Part2") {
        test("Solve with example data") {
            Day5.solvePart2(example1Data) shouldBeEqual 123
        }
    }
})
