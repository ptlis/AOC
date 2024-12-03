package uk.org.fca.adventofcode.y2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2024.day3.Day3

class Day3Test: FunSpec({
    val example1Data = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

    context("Part 1") {
        test("solve example 1") {
            Day3.solvePart1(listOf(example1Data)) shouldBeEqual 161
        }
    }
})
