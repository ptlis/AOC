package uk.org.fca.adventofcode.y2023.day7

import java.math.BigInteger

data class Game(val hands: List<Hand>) {
    val sortedHands get(): List<Hand> {
        val handsArray = hands.toTypedArray()
        handsArray.sort()
        return handsArray.toList()
    }

    val winnings get(): BigInteger {
        return sortedHands.mapIndexed { index, hand -> BigInteger.valueOf(((index + 1) * hand.bid).toLong()) }.sumOf { it }
    }

    companion object {
        fun parse(game: List<String>): Game {
            return Game(game.map { Hand.parse(it) })
        }
    }
}
