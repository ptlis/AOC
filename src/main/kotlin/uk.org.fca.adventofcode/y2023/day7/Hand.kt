package uk.org.fca.adventofcode.y2023.day7

import java.lang.RuntimeException

enum class HandType {
    FIVE_OF_A_KIND {
        override val rank = 7
    },
    FOUR_OF_A_KIND {
        override val rank = 6
    },
    FULL_HOUSE {
        override val rank = 5
    },
    THREE_OF_A_KIND {
        override val rank = 4
    },
    TWO_PAIR {
        override val rank = 3
    },
    ONE_PAIR {
        override val rank = 2
    },
    HIGH_CARD {
        override val rank = 1
    };

    abstract val rank: Int
}

enum class Card() {
    ACE {
        override val rank = 14
    },
    KING {
        override val rank = 13
    },
    QUEEN {
        override val rank = 12
    },
    JACK {
        override val rank = 11
    },
    TEN {
        override val rank = 10
    },
    NINE {
        override val rank = 9
    },
    EIGHT {
        override val rank = 8
    },
    SEVEN {
        override val rank = 7
    },
    SIX {
        override val rank = 6
    },
    FIVE {
        override val rank = 5
    },
    FOUR {
        override val rank = 4
    },
    THREE {
        override val rank = 3
    },
    TWO {
        override val rank = 2
    };

    abstract val rank: Int

    companion object {
        fun parse(card: Char): Card {
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
                'J' to JACK,
                'Q' to QUEEN,
                'K' to KING,
                'A' to ACE
            )

            return mapping[card] ?: throw RuntimeException("Unknown card type \"$card\" encountered")
        }
    }
}

data class Hand(val cards: List<Card>, val bid: Int): Comparable<Hand> {
    val rank get(): Int = cards.indices.sumOf { cards[it].rank * 13 * (cards.size - it) }

    val type get(): HandType {
        val groupedCards = cards.groupBy { it }.values.toList().sortedBy { it.size }.reversed()

        return when {
            // Five of a kind, where all five cards have the same label: AAAAA
            (groupedCards.size == 1) -> HandType.FIVE_OF_A_KIND

            // Four of a kind, where four cards have the same label and one card has a different label: AA8AA
            (groupedCards.size == 2 && groupedCards.first().size == 4) -> HandType.FOUR_OF_A_KIND

            // Full house, where three cards have the same label, and the remaining two cards share a different label:
            //  23332
            (groupedCards.size == 2 && groupedCards.first().size == 3) -> HandType.FULL_HOUSE

            // Three of a kind, where three cards have the same label, and the remaining two cards are each different
            //  from any other card in the hand: TTT98
            (groupedCards.size == 3 && groupedCards.first().size == 3) -> HandType.THREE_OF_A_KIND

            // Two pair, where two cards share one label, two other cards share a second label, and the remaining card
            //  has a third label: 23432
            (groupedCards.size == 3 && groupedCards.first().size == 2 && groupedCards.last().size == 1) -> HandType.TWO_PAIR

            // One pair, where two cards share one label, and the other three cards have a different label from the pair
            //  and each other: A23A4
            (groupedCards.size == 4) -> HandType.ONE_PAIR

            // High card, where all cards' labels are distinct: 23456
            else -> HandType.HIGH_CARD
        }
    }

    override fun compareTo(other: Hand): Int {
        val handType = type.rank.compareTo(other.type.rank)
        if (handType != 0) {
            return handType
        }

        // Bigger cards win
        cards.indices.forEach {
            if (cards[it].rank > other.cards[it].rank) return 1
            if (cards[it].rank < other.cards[it].rank) return -1
        }

        return 0
    }

    companion object {
        fun parse(hand: String): Hand {
            val (cards, bid) = hand.split(" ")

            val handData = cards.toCharArray()
            return Hand(handData.map { Card.parse(it) }, bid.toInt())
        }
    }
}
