package uk.org.fca.adventofcode.`2023`

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

data class RaceResult(val timeInMs: Int, val distanceInMM: Int) {
    val winningStrategies get() = (0..timeInMs).toList().filter { ((timeInMs - it) * it) > distanceInMM }
}

data class RaceResults(val results: List<RaceResult>) {
    val marginOfError get(): Int = results.map { it.winningStrategies.size }.reduce { acc, next -> acc * next }

    companion object {
        fun parse(data: List<String>): RaceResults {
            val times = data[0].split(" ").filter { it.isNotEmpty() && it != "Time:" }
            val distances = data[1].split(" ").filter { it.isNotEmpty() && it != "Distance:" }

            return RaceResults(times.indices.map { RaceResult(times[it].toInt(), distances[it].toInt()) })
        }
    }
}

class Day6: Day {
    override fun part1Solution(data: List<String>): BigInteger {
        val results = RaceResults.parse(data)
        return BigInteger.valueOf(results.marginOfError.toLong())
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return BigInteger.valueOf(-1)
    }
}