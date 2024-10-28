package uk.org.fca.adventofcode.`2023`

import uk.org.fca.adventofcode.Part1
import uk.org.fca.adventofcode.Part2

data class Scratchcard(val cardNumber: Int, val winningNumbers: Set<Int>, val cardNumbers: Set<Int>) {
    val winningOnThisCard
        get() = cardNumbers.intersect(winningNumbers)

    val points
        get() = winningOnThisCard.fold(1) { acc, _ -> acc * 2 } / 2
}

class Day4: Part1, Part2 {
    override fun part1Solution(data: List<String>): Int {
        return data.map { parseCardData(it) }.sumOf { it.points }}

    override fun part2Solution(data: List<String>): Int {
        return calculateScratchcardCount(data.map { parseCardData(it) })
    }
}

fun calculateScratchcardCount(scratchcards: List<Scratchcard>): Int {
    val cardCounts = scratchcards.associateBy({ it.cardNumber }, { 1 }).toMutableMap()
    scratchcards.forEach {
        val winningCount = it.winningOnThisCard.size

        for (i in 1..winningCount) {
            if (it.cardNumber + i <= scratchcards.size) {
                cardCounts[it.cardNumber + i] = (cardCounts[it.cardNumber + i] ?: 0) + (cardCounts[it.cardNumber] ?: 0)
            }
        }
    }
    return cardCounts.values.toList().sum()
}

fun parseCardData(rawCardData: String): Scratchcard {
    val (rawCardNumber, rawNumberData) = rawCardData.split(":")
    val (rawWinningNumbers, rawCardNumbers) = rawNumberData.split("|")
    return Scratchcard(
        rawCardNumber.split(" ").last().toInt(),
        rawWinningNumbers.split(" ").filter { it != "" }.map { it.toInt() }.toSet(),
        rawCardNumbers.split(" ").filter { it != "" }.map { it.toInt() }.toSet(),
    )
}