package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day4.Scratchcard
import uk.org.fca.adventofcode.y2023.day4.Scratchcards
import java.math.BigInteger

class Day4Test: FunSpec({
    val scratchcards = Scratchcards.parse("""
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    """.trimIndent().lines())

    context("Part 1") {
        test("Parse scratchcard") {
            Scratchcard.parse("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53") shouldBeEqual Scratchcard(
                1,
                setOf(41, 48, 83, 86, 17),
                setOf(83, 86, 6, 31, 17, 9, 48, 53)
            )
        }

        test("Calculate winning numbers") {
            val scratchcard = Scratchcard(1, setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))
            scratchcard.winningOnThisCard shouldBeEqual setOf(48, 83, 17, 86)
        }

        test("Calculate points") {
            val scratchcard = Scratchcard(1, setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))
            scratchcard.points shouldBeEqual 8
        }
    }

    context("Part 2") {
        test("Calculate points") {
            scratchcards.points shouldBeEqual BigInteger.valueOf(13)
        }

        test("Calculate scratch card count") {
            scratchcards.scratchCardCount shouldBeEqual BigInteger.valueOf(30)
        }
    }
})
