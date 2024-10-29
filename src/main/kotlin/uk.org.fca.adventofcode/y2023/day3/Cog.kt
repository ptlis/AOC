package uk.org.fca.adventofcode.y2023.day3

data class Cog(val partNumbers: List<PartNumber>) {
    val ratio
        get() = this.partNumbers.fold(1) { acc, next -> acc * next.number }

    companion object {
        fun findCogs(partNumbers: List<PartNumber>) = partNumbers
            .filter { it.symbol.symbol == '*' }
            .groupBy({"${it.symbol.position.x},${it.symbol.position.y}"}) { it }
            .filter { it.value.size > 1 }
            .map { Cog(it.value) }
    }
}
