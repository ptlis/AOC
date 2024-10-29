package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day7.Card
import uk.org.fca.adventofcode.y2023.day7.Game
import uk.org.fca.adventofcode.y2023.day7.Hand
import uk.org.fca.adventofcode.y2023.day7.HandType
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
            Hand.parse(handsData[0]) shouldBeEqual Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765)
        }

        test("parsing a game") {
            Game.parse(handsData) shouldBeEqual Game(listOf(
                Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765),
                Hand(listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE), 684),
                Hand(listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 28),
                Hand(listOf(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN), 220),
                Hand(listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE), 483),
            ))
        }

        context("calculating hand type") {
            withData(
                Pair(HandType.FIVE_OF_A_KIND, Hand(listOf(Card.TWO, Card.TWO, Card.TWO, Card.TWO, Card.TWO), 765)),
                Pair(HandType.FOUR_OF_A_KIND, Hand(listOf(Card.NINE, Card.TWO, Card.NINE, Card.NINE, Card.NINE), 765)),
                Pair(HandType.FULL_HOUSE, Hand(listOf(Card.TWO, Card.TWO, Card.SEVEN, Card.SEVEN, Card.TWO), 765)),
                Pair(HandType.THREE_OF_A_KIND, Hand(listOf(Card.TWO, Card.TWO, Card.SEVEN, Card.NINE, Card.TWO), 765)),
                Pair(HandType.TWO_PAIR, Hand(listOf(Card.TWO, Card.TWO, Card.SEVEN, Card.NINE, Card.SEVEN), 765)),
                Pair(HandType.HIGH_CARD, Hand(listOf(Card.TWO, Card.THREE, Card.FOUR, Card.FIVE, Card.SIX), 765))
            ) {
                handData -> handData.second.type shouldBeEqual handData.first
            }
        }

        test("sort hands") {
            Game.parse(handsData).sortedHands shouldBeEqual listOf(
                Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765),
                Hand(listOf(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN), 220),
                Hand(listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 28),
                Hand(listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE), 684),
                Hand(listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE), 483)
            )
        }

        test("calculate game winnings") {
            Game.parse(handsData).winnings shouldBeEqual BigInteger.valueOf(6440)
        }
    }
})
