package uk.org.fca.adventofcode.y2023.day6

import uk.org.fca.adventofcode.rangeTo
import java.math.BigInteger

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
