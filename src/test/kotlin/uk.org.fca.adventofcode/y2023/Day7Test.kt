package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day7.Game
import uk.org.fca.adventofcode.y2023.day7.Hand
import java.math.BigInteger

class Day7Test: FunSpec({
    val handsData = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483""".trimIndent().lines()

    context("Part 1") {
        test("parsing a hand") {
            Hand.parse(handsData[0]) shouldBeEqual Hand(listOf(Hand.Card.THREE, Hand.Card.TWO, Hand.Card.TEN, Hand.Card.THREE, Hand.Card.KING), 765)
        }

        test("parsing a game") {
            Game.parse(handsData) shouldBeEqual Game(listOf(
                Hand(listOf(Hand.Card.THREE, Hand.Card.TWO, Hand.Card.TEN, Hand.Card.THREE, Hand.Card.KING), 765),
                Hand(listOf(Hand.Card.TEN, Hand.Card.FIVE, Hand.Card.FIVE, Hand.Card.JACK, Hand.Card.FIVE), 684),
                Hand(listOf(Hand.Card.KING, Hand.Card.KING, Hand.Card.SIX, Hand.Card.SEVEN, Hand.Card.SEVEN), 28),
                Hand(listOf(Hand.Card.KING, Hand.Card.TEN, Hand.Card.JACK, Hand.Card.JACK, Hand.Card.TEN), 220),
                Hand(listOf(Hand.Card.QUEEN, Hand.Card.QUEEN, Hand.Card.QUEEN, Hand.Card.JACK, Hand.Card.ACE), 483),
            ))
        }

        context("calculating hand type") {
            withData(
                Pair(Hand.Type.FIVE_OF_A_KIND, Hand(listOf(Hand.Card.TWO, Hand.Card.TWO, Hand.Card.TWO, Hand.Card.TWO, Hand.Card.TWO), 765)),
                Pair(Hand.Type.FOUR_OF_A_KIND, Hand(listOf(Hand.Card.NINE, Hand.Card.TWO, Hand.Card.NINE, Hand.Card.NINE, Hand.Card.NINE), 765)),
                Pair(Hand.Type.FULL_HOUSE, Hand(listOf(Hand.Card.TWO, Hand.Card.TWO, Hand.Card.SEVEN, Hand.Card.SEVEN, Hand.Card.TWO), 765)),
                Pair(Hand.Type.THREE_OF_A_KIND, Hand(listOf(Hand.Card.TWO, Hand.Card.TWO, Hand.Card.SEVEN, Hand.Card.NINE, Hand.Card.TWO), 765)),
                Pair(Hand.Type.TWO_PAIR, Hand(listOf(Hand.Card.TWO, Hand.Card.TWO, Hand.Card.SEVEN, Hand.Card.NINE, Hand.Card.SEVEN), 765)),
                Pair(Hand.Type.HIGH_CARD, Hand(listOf(Hand.Card.TWO, Hand.Card.THREE, Hand.Card.FOUR, Hand.Card.FIVE, Hand.Card.SIX), 765))
            ) {
                handData -> handData.second.type shouldBeEqual handData.first
            }
        }

        test("sort hands") {
            Game.parse(handsData).sortedHands shouldBeEqual listOf(
                Hand(listOf(Hand.Card.THREE, Hand.Card.TWO, Hand.Card.TEN, Hand.Card.THREE, Hand.Card.KING), 765),
                Hand(listOf(Hand.Card.KING, Hand.Card.TEN, Hand.Card.JACK, Hand.Card.JACK, Hand.Card.TEN), 220),
                Hand(listOf(Hand.Card.KING, Hand.Card.KING, Hand.Card.SIX, Hand.Card.SEVEN, Hand.Card.SEVEN), 28),
                Hand(listOf(Hand.Card.TEN, Hand.Card.FIVE, Hand.Card.FIVE, Hand.Card.JACK, Hand.Card.FIVE), 684),
                Hand(listOf(Hand.Card.QUEEN, Hand.Card.QUEEN, Hand.Card.QUEEN, Hand.Card.JACK, Hand.Card.ACE), 483)
            )
        }

        test("calculate game winnings") {
            Game.parse(handsData).winnings shouldBeEqual BigInteger.valueOf(6440)
        }
    }
})
