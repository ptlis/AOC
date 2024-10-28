package uk.org.fca.adventofcode.y2023.day6

import java.math.BigInteger

data class RaceResults(val results: List<RaceResult>) {
    val marginOfError get(): BigInteger = results.map { it.winningStrategyCount }.reduce { acc, next -> acc * next }

    companion object {
        fun parse(data: List<String>): RaceResults {
            val times = data[0].split(" ").filter { it.isNotEmpty() && it != "Time:" }
            val distances = data[1].split(" ").filter { it.isNotEmpty() && it != "Distance:" }

            return RaceResults(times.indices.map { RaceResult(times[it].toBigInteger(), distances[it].toBigInteger()) })
        }
    }
}
