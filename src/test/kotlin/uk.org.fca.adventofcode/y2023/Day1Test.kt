package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day1.Calibration


class Day1Test: FunSpec({
    context("Part 1") {
        test("test example calibration document") {
            val calibration = Calibration.parse(listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"), false)
            calibration shouldBeEqual Calibration(listOf(12, 38, 15, 77))
        }
    }

    context("Part 2") {
        test("test example calibration document") {
            val calibration = Calibration.parse(listOf(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
            ), true)

            calibration shouldBeEqual Calibration(listOf(29, 83, 13, 24, 42, 14, 76))
        }
    }
})
