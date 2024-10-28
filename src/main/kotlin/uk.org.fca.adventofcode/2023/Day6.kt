package uk.org.fca.adventofcode.`2023`

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import uk.org.fca.adventofcode.rangeTo

data class RaceResult(val timeInMs: BigInteger, val distanceInMM: BigInteger) {
    val winningStrategyCount get(): BigInteger {
        var smallestWinningTime = BigInteger.ZERO
        var largestWinningTime = BigInteger.ZERO

        for (time in BigInteger.ZERO..timeInMs) {
            if (time * (timeInMs - time) > distanceInMM) {
                smallestWinningTime = time
            }

            val remainTime = timeInMs.subtract(time)
            if (remainTime * (timeInMs - remainTime) > distanceInMM) {
                largestWinningTime = remainTime
            }

            if (smallestWinningTime != BigInteger.ZERO && largestWinningTime != BigInteger.ZERO) {
                break
            }
        }

        return largestWinningTime.add(BigInteger.ONE).subtract(smallestWinningTime)
    }

    companion object {
        fun parse(data: List<String>): RaceResult {
            val time = data[0].split(" ")
                .filter { it.isNotEmpty() && it != "Time:" }
                .joinToString("")
                .toBigInteger()
            val distance = data[1].split(" ")
                .filter { it.isNotEmpty() && it != "Distance:" }
                .joinToString("")
                .toBigInteger()

            return RaceResult(time, distance)
        }
    }
}

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

class Day6: Day {
    override fun part1Solution(data: List<String>): BigInteger {
        val results = RaceResults.parse(data)
        return BigInteger.valueOf(results.marginOfError.toLong())
    }

    override fun part2Solution(data: List<String>): BigInteger {
        val result = RaceResult.parse(data)
        return BigInteger.valueOf(result.winningStrategyCount.toLong())
    }
}