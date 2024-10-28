package uk.org.fca.adventofcode.y2023.day4

import java.math.BigInteger

data class Scratchcards(val scratchcards: List<Scratchcard>) {
    val points get() = scratchcards.sumOf { it.points }.toBigInteger()

    val scratchCardCount get(): BigInteger {
        val cardCounts = scratchcards.associateBy({ it.cardNumber }, { 1 }).toMutableMap()
        scratchcards.forEach {
            val winningCount = it.winningOnThisCard.size

            for (i in 1..winningCount) {
                if (it.cardNumber + i <= scratchcards.size) {
                    cardCounts[it.cardNumber + i] = (cardCounts[it.cardNumber + i] ?: 0) + (cardCounts[it.cardNumber] ?: 0)
                }
            }
        }
        return cardCounts.values.toList().sum().toBigInteger()
    }

    companion object {
        fun parse(rawCardsData: List<String>): Scratchcards {
            return Scratchcards(rawCardsData.map { Scratchcard.parse(it) })
        }
    }
}
