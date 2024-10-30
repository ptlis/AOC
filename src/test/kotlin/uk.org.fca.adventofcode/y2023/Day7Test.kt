package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day7.Card
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
            Hand.parse(handsData[0], Card.Type.JACK) shouldBeEqual Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.TWO), Card(Card.Type.TEN), Card(Card.Type.THREE), Card(Card.Type.KING)), 765)
        }

        test("parsing a game") {
            Game.parse(handsData, Card.Type.JACK) shouldBeEqual Game(listOf(
                Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.TWO), Card(Card.Type.TEN), Card(Card.Type.THREE), Card(Card.Type.KING)), 765),
                Hand(listOf(Card(Card.Type.TEN), Card(Card.Type.FIVE), Card(Card.Type.FIVE), Card(Card.Type.JACK), Card(Card.Type.FIVE)), 684),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.KING), Card(Card.Type.SIX), Card(Card.Type.SEVEN), Card(Card.Type.SEVEN)), 28),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.TEN), Card(Card.Type.JACK), Card(Card.Type.JACK), Card(Card.Type.TEN)), 220),
                Hand(listOf(Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.JACK), Card(Card.Type.ACE)), 483),
            ))
        }

        context("calculating hand type") {
            withData(
                Pair(Hand.Type.FIVE_OF_A_KIND, Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO)), 765)),
                Pair(Hand.Type.FOUR_OF_A_KIND, Hand(listOf(Card(Card.Type.NINE), Card(Card.Type.TWO), Card(Card.Type.NINE), Card(Card.Type.NINE), Card(Card.Type.NINE)), 765)),
                Pair(Hand.Type.FULL_HOUSE, Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.SEVEN), Card(Card.Type.SEVEN), Card(Card.Type.TWO)), 765)),
                Pair(Hand.Type.THREE_OF_A_KIND, Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.SEVEN), Card(Card.Type.NINE), Card(Card.Type.TWO)), 765)),
                Pair(Hand.Type.TWO_PAIR, Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.SEVEN), Card(Card.Type.NINE), Card(Card.Type.SEVEN)), 765)),
                Pair(Hand.Type.HIGH_CARD, Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.THREE), Card(Card.Type.FOUR), Card(Card.Type.FIVE), Card(Card.Type.SIX)), 765))
            ) {
                data -> data.second.type shouldBeEqual data.first
            }
        }

        test("sort hands") {
            Game.parse(handsData, Card.Type.JACK).sortedHands shouldBeEqual listOf(
                Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.TWO), Card(Card.Type.TEN), Card(Card.Type.THREE), Card(Card.Type.KING)), 765),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.TEN), Card(Card.Type.JACK), Card(Card.Type.JACK), Card(Card.Type.TEN)), 220),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.KING), Card(Card.Type.SIX), Card(Card.Type.SEVEN), Card(Card.Type.SEVEN)), 28),
                Hand(listOf(Card(Card.Type.TEN), Card(Card.Type.FIVE), Card(Card.Type.FIVE), Card(Card.Type.JACK), Card(Card.Type.FIVE)), 684),
                Hand(listOf(Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.JACK), Card(Card.Type.ACE)), 483)
            )
        }

        test("calculate game winnings") {
            Game.parse(handsData, Card.Type.JACK).winnings shouldBeEqual BigInteger.valueOf(6440)
        }
    }

    context("Part 2") {
        test("parsing a hand") {
            Hand.parse(handsData[1], Card.Type.JOKER) shouldBeEqual Hand(listOf(Card(Card.Type.TEN), Card(Card.Type.FIVE), Card(Card.Type.FIVE), Card(Card.Type.JOKER), Card(Card.Type.FIVE)), 684)
        }

        context("Calculate type") {
            withData(
                Pair(Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.TWO), Card(Card.Type.TEN), Card(Card.Type.THREE), Card(Card.Type.KING)), 765), Hand.Type.ONE_PAIR),
                Pair(Hand(listOf(Card(Card.Type.TEN), Card(Card.Type.FIVE), Card(Card.Type.FIVE), Card(Card.Type.JOKER), Card(Card.Type.FIVE)), 684), Hand.Type.FOUR_OF_A_KIND),
                Pair(Hand(listOf(Card(Card.Type.KING), Card(Card.Type.KING), Card(Card.Type.SIX), Card(Card.Type.SEVEN), Card(Card.Type.SEVEN)), 28), Hand.Type.TWO_PAIR),
                Pair(Hand(listOf(Card(Card.Type.KING), Card(Card.Type.TEN), Card(Card.Type.JOKER), Card(Card.Type.JOKER), Card(Card.Type.TEN)), 220), Hand.Type.FOUR_OF_A_KIND),
                Pair(Hand(listOf(Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.JOKER), Card(Card.Type.ACE)), 483), Hand.Type.FOUR_OF_A_KIND),
                Pair(Hand(listOf(Card(Card.Type.JOKER), Card(Card.Type.JOKER), Card(Card.Type.JOKER), Card(Card.Type.JOKER), Card(Card.Type.JOKER)), 1234), Hand.Type.FIVE_OF_A_KIND),
            ) {
                data -> data.first.type shouldBeEqual data.second
            }
        }

        test("parsing a game") {
            Game.parse(handsData, Card.Type.JOKER) shouldBeEqual Game(listOf(
                Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.TWO), Card(Card.Type.TEN), Card(Card.Type.THREE), Card(Card.Type.KING)), 765),
                Hand(listOf(Card(Card.Type.TEN), Card(Card.Type.FIVE), Card(Card.Type.FIVE), Card(Card.Type.JOKER), Card(Card.Type.FIVE)), 684),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.KING), Card(Card.Type.SIX), Card(Card.Type.SEVEN), Card(Card.Type.SEVEN)), 28),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.TEN), Card(Card.Type.JOKER), Card(Card.Type.JOKER), Card(Card.Type.TEN)), 220),
                Hand(listOf(Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.JOKER), Card(Card.Type.ACE)), 483),
            ))
        }

        test("sort hands") {
            Game.parse(handsData, Card.Type.JOKER).sortedHands shouldBeEqual listOf(
                Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.TWO), Card(Card.Type.TEN), Card(Card.Type.THREE), Card(Card.Type.KING)), 765),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.KING), Card(Card.Type.SIX), Card(Card.Type.SEVEN), Card(Card.Type.SEVEN)), 28),
                Hand(listOf(Card(Card.Type.TEN), Card(Card.Type.FIVE), Card(Card.Type.FIVE), Card(Card.Type.JOKER), Card(Card.Type.FIVE)), 684),
                Hand(listOf(Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.QUEEN), Card(Card.Type.JOKER), Card(Card.Type.ACE)), 483),
                Hand(listOf(Card(Card.Type.KING), Card(Card.Type.TEN), Card(Card.Type.JOKER), Card(Card.Type.JOKER), Card(Card.Type.TEN)), 220),
            )
        }

        test("sort hands, some rely on joker") {
            val sortedHands = Game(listOf(
                Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.THREE), Card(Card.Type.THREE), Card(Card.Type.THREE), Card(Card.Type.THREE)), 3),
                Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.JOKER)), 2),
                Hand(listOf(Card(Card.Type.FOUR), Card(Card.Type.FOUR), Card(Card.Type.FOUR), Card(Card.Type.JOKER), Card(Card.Type.JOKER)), 4),
            )).sortedHands

            sortedHands shouldBeEqual listOf(
                Hand(listOf(Card(Card.Type.FOUR), Card(Card.Type.FOUR), Card(Card.Type.FOUR), Card(Card.Type.JOKER), Card(Card.Type.JOKER)), 4),
                Hand(listOf(Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.TWO), Card(Card.Type.JOKER)), 2),
                Hand(listOf(Card(Card.Type.THREE), Card(Card.Type.THREE), Card(Card.Type.THREE), Card(Card.Type.THREE), Card(Card.Type.THREE)), 3),
            )
        }

        test("calculate game winnings") {
            Game.parse(handsData, Card.Type.JOKER).winnings shouldBeEqual BigInteger.valueOf(5905)
        }
    }
})
