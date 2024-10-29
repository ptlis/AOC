package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day6.RaceResult
import uk.org.fca.adventofcode.y2023.day6.RaceResults
import java.math.BigInteger

class Day6Test: FunSpec({
    val testRaceResultData = """
        Time:      7  15   30
        Distance:  9  40  200""".trimIndent().lines()

    context("Part 1") {
        context("calculate winning strategies count") {
            withData(
                Pair(BigInteger.valueOf(4), RaceResult(BigInteger.valueOf(7), BigInteger.valueOf(9))),
                Pair(BigInteger.valueOf(8), RaceResult(BigInteger.valueOf(15), BigInteger.valueOf(40))),
                Pair(BigInteger.valueOf(9), RaceResult(BigInteger.valueOf(30), BigInteger.valueOf(200)))
            ) {
                resultData -> resultData.second.winningStrategyCount shouldBeEqual resultData.first
            }
        }

        test("calculate margin of error") {
            RaceResults.parse(testRaceResultData).marginOfError shouldBeEqual BigInteger.valueOf(288)
        }
    }

    context("Part 2") {
        test("parse as single result") {
            RaceResult.parse(testRaceResultData) shouldBeEqual RaceResult(BigInteger.valueOf(71530), BigInteger.valueOf(940200))
        }

        context("calculate winning strategies count") {
            val raceResult = RaceResult(BigInteger.valueOf(71530), BigInteger.valueOf(940200))
            raceResult.winningStrategyCount shouldBeEqual BigInteger.valueOf(71503)
        }
    }
})
