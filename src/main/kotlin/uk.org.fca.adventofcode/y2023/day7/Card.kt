package uk.org.fca.adventofcode.y2023.day7

import uk.org.fca.adventofcode.y2023.day7.Card.Type.*
import java.lang.RuntimeException

data class Card(val type: Type) {
    enum class Type {
        ACE,
        KING,
        QUEEN,
        JACK,
        TEN,
        NINE,
        EIGHT,
        SEVEN,
        SIX,
        FIVE,
        FOUR,
        THREE,
        TWO,
        JOKER;
    }

    val rank get() = when (type) {
        ACE -> 14
        KING -> 13
        QUEEN -> 12
        JACK -> 11
        TEN -> 10
        NINE -> 9
        EIGHT -> 8
        SEVEN -> 7
        SIX -> 6
        FIVE -> 5
        FOUR -> 4
        THREE -> 3
        TWO -> 2
        JOKER -> 1
    }

    override fun toString(): String {
        return mapOf(
            JOKER to "J",
            TWO to "2",
            THREE to "3",
            FOUR to "4",
            FIVE to "5",
            SIX to "6",
            SEVEN to "7",
            EIGHT to "8",
            NINE to "9",
            TEN to "T",
            JACK to "J",
            QUEEN to "Q",
            KING to "K",
            ACE to "A"
        )[type] ?: throw RuntimeException("Unknown card type: $type")
    }

    companion object {
        fun parse(card: Char, jType: Type): Card {
            val mapping = mapOf(
                '2' to TWO,
                '3' to THREE,
                '4' to FOUR,
                '5' to FIVE,
                '6' to SIX,
                '7' to SEVEN,
                '8' to EIGHT,
                '9' to NINE,
                'T' to TEN,
                'J' to jType,
                'Q' to QUEEN,
                'K' to KING,
                'A' to ACE
            )

            return Card(mapping[card] ?: throw RuntimeException("Unknown card type \"$card\" encountered"))
        }
    }
}
