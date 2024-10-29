package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.y2023.day7.Card
import uk.org.fca.adventofcode.y2023.day7.Game
import uk.org.fca.adventofcode.y2023.day7.Hand
import uk.org.fca.adventofcode.y2023.day7.HandType
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    private val handsData = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483""".trimIndent().lines()

    @Test
    fun testParseHand() {
        val expected = Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765)

        assertEquals(expected, Hand.parse(handsData[0]))
    }

    @Test
    fun testParseGame() {
        val expected = Game(listOf(
            Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765),
            Hand(listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE), 684),
            Hand(listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 28),
            Hand(listOf(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN), 220),
            Hand(listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE), 483),
        ))

        assertEquals(expected, Game.parse(handsData))
    }

    @Test
    fun testPart1HandTypeFiveOfAKind() {
        val expected = HandType.FIVE_OF_A_KIND

        assertEquals(expected, Hand(listOf(Card.TWO, Card.TWO, Card.TWO, Card.TWO, Card.TWO), 765).type)
    }

    @Test
    fun testPart1HandTypeFourOfAKind() {
        val expected = HandType.FOUR_OF_A_KIND

        assertEquals(expected, Hand(listOf(Card.NINE, Card.TWO, Card.NINE, Card.NINE, Card.NINE), 765).type)
    }

    @Test
    fun testPart1HandTypeFullHouse() {
        val expected = HandType.FULL_HOUSE

        assertEquals(expected, Hand(listOf(Card.TWO, Card.TWO, Card.SEVEN, Card.SEVEN, Card.TWO), 765).type)
    }

    @Test
    fun testPart1HandTypeThreeOfAKind() {
        val expected = HandType.THREE_OF_A_KIND

        assertEquals(expected, Hand(listOf(Card.TWO, Card.TWO, Card.SEVEN, Card.NINE, Card.TWO), 765).type)
    }

    @Test
    fun testPart1HandTypeTwoPair() {
        val expected = HandType.TWO_PAIR

        assertEquals(expected, Hand(listOf(Card.TWO, Card.TWO, Card.SEVEN, Card.NINE, Card.SEVEN), 765).type)
    }

    @Test
    fun testPart1HandTypeHighCard() {
        val expected = HandType.HIGH_CARD

        assertEquals(expected, Hand(listOf(Card.TWO, Card.THREE, Card.FOUR, Card.FIVE, Card.SIX), 765).type)
    }

    @Test
    fun testPart1GameSortedHands() {
        val expected = listOf(
            Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765),
            Hand(listOf(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN), 220),
            Hand(listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 28),
            Hand(listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE), 684),
            Hand(listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE), 483)
        )

        assertEquals(expected, Game.parse(handsData).sortedHands)
    }

    @Test
    fun testPart1GameWinnings() {
        val expected = BigInteger.valueOf(6440)

        assertEquals(expected, Game.parse(handsData).winnings)
    }
}
