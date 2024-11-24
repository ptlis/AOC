package uk.org.fca.adventofcode.common

fun <T> runLengthEncode(configuration: List<T>): List<Pair<T, Int>> {
    val rleAccumulator = mutableListOf<MutableList<T>>()
    configuration.forEach {
        if (rleAccumulator.isEmpty() || rleAccumulator.last().first() != it) {
            rleAccumulator.add(mutableListOf(it))
        } else {
            rleAccumulator.last().add(it)
        }
    }
    return rleAccumulator.map { Pair(it.first(), it.size) }
}