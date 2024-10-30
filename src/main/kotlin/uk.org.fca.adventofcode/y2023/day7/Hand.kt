package uk.org.fca.adventofcode.y2023.day7

import java.lang.RuntimeException

data class Hand(val cards: List<Card>, val bid: Int): Comparable<Hand> {
    enum class Type {
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

    val type get(): Type {
        val groupedCards = cards.groupBy { it.type }.toMutableMap()
        val jokers = groupedCards[Card.Type.JOKER] ?: listOf()
        groupedCards.remove(Card.Type.JOKER)

        var processedCards = groupedCards.values.toList().sortedBy { it.size }.reversed().toMutableList()
        if (processedCards.isEmpty()) {
            processedCards.add(jokers)
        }
        else if (processedCards.first().size > 1) {
            processedCards[0] = processedCards.first() + jokers
        }

        return when {
            // Five of a kind, where all five cards have the same label: AAAAA
            (processedCards.size == 1) -> Type.FIVE_OF_A_KIND

            // Four of a kind, where four cards have the same label and one card has a different label: AA8AA
            (processedCards.size == 2 && processedCards.first().size == 4) -> Type.FOUR_OF_A_KIND

            // Full house, where three cards have the same label, and the remaining two cards share a different label:
            //  23332
            (processedCards.size == 2 && processedCards.first().size == 3) -> Type.FULL_HOUSE

            // Three of a kind, where three cards have the same label, and the remaining two cards are each different
            //  from any other card in the hand: TTT98
            (processedCards.size == 3 && processedCards.first().size == 3) -> Type.THREE_OF_A_KIND

            // Two pair, where two cards share one label, two other cards share a second label, and the remaining card
            //  has a third label: 23432
            (processedCards.size == 3 && processedCards.first().size == 2 && processedCards.last().size == 1) -> Type.TWO_PAIR

            // One pair, where two cards share one label, and the other three cards have a different label from the pair
            //  and each other: A23A4
            (processedCards.size == 4) -> Type.ONE_PAIR

            // High card, where all cards' labels are distinct: 23456
            else -> Type.HIGH_CARD
        }
    }

    override fun compareTo(other: Hand): Int {
        val handTypeComparison = type.rank.compareTo(other.type.rank)
        if (handTypeComparison != 0) {
            return handTypeComparison
        } else {
            val thisJokers = this.cards.filter { it.type == Card.Type.JOKER }.size
            val otherJokers = other.cards.filter { it.type == Card.Type.JOKER }.size
            if (thisJokers != otherJokers) {
                return otherJokers - thisJokers
            }
        }

        // Bigger cards win
        cards.indices.forEach {
            if (cards[it].rank > other.cards[it].rank) return 1
            if (cards[it].rank < other.cards[it].rank) return -1
        }

        return 0
    }

    override fun toString(): String {
        return cards.joinToString("") + " " + bid
    }

    companion object {
        fun parse(hand: String, jIsType: Card.Type): Hand {
            val (cards, bid) = hand.split(" ")

            val handData = cards.toCharArray()
            return Hand(handData.map { Card.parse(it, jIsType) }, bid.toInt())
        }
    }
}
