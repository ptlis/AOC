package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day9.Day9
import java.math.BigInteger

class Day9Test: FunSpec({
    context("Part 1") {
        test("Parse Single") {
            val input =
                "0 7 18 31 55 131 378 1093 2953 7398 17321 38257 80352 161507 312219 582730 1053014 1845605 3139788 5182383 8283893"

            Day9().parseSingle(input) shouldBeEqual listOf(
                BigInteger("0"),
                BigInteger("7"),
                BigInteger("18"),
                BigInteger("31"),
                BigInteger("55"),
                BigInteger("131"),
                BigInteger("378"),
                BigInteger("1093"),
                BigInteger("2953"),
                BigInteger("7398"),
                BigInteger("17321"),
                BigInteger("38257"),
                BigInteger("80352"),
                BigInteger("161507"),
                BigInteger("312219"),
                BigInteger("582730"),
                BigInteger("1053014"),
                BigInteger("1845605"),
                BigInteger("3139788"),
                BigInteger("5182383"),
                BigInteger("8283893"),
            )
        }

        context("Examples") {
            withData(mapOf(
                "Example 1" to Pair("0 3 6 9 12 15", BigInteger("18")),
                "Example 2" to Pair("1 3 6 10 15 21", BigInteger("28")),
                "Example 3" to Pair("10 13 16 21 30 45", BigInteger("68")),
            )) {
                data -> Day9().solveSingle(data.first) shouldBeEqual data.second
            }
        }
    }
})
