package uk.org.fca.scratchcards

import java.io.File

data class Scratchcard(val cardNumber: Int, val winningNumbers: Set<Int>, val cardNumbers: Set<Int>) {
    val winningOnThisCard
        get() = cardNumbers.intersect(winningNumbers)

    val points
        get() = winningOnThisCard.fold(1) { acc, _ -> acc * 2 } / 2
}

fun main () {
    val scratchcards = File("data/scratchcards")
        .useLines { it.toSet() }
        .map { parseCardData(it) }

    println("Part1: ${scratchcards.sumOf { it.points }}")
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