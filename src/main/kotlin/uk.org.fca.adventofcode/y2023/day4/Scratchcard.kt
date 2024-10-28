package uk.org.fca.adventofcode.y2023.day4

data class Scratchcard(val cardNumber: Int, val winningNumbers: Set<Int>, val cardNumbers: Set<Int>) {
    val winningOnThisCard
        get() = cardNumbers.intersect(winningNumbers)

    val points
        get() = winningOnThisCard.fold(1) { acc, _ -> acc * 2 } / 2

    companion object {
        fun parse(rawCardData: String): Scratchcard {
            val (rawCardNumber, rawNumberData) = rawCardData.split(":")
            val (rawWinningNumbers, rawCardNumbers) = rawNumberData.split("|")
            return Scratchcard(
                rawCardNumber.split(" ").last().toInt(),
                rawWinningNumbers.split(" ").filter { it != "" }.map { it.toInt() }.toSet(),
                rawCardNumbers.split(" ").filter { it != "" }.map { it.toInt() }.toSet(),
            )
        }
    }
}
