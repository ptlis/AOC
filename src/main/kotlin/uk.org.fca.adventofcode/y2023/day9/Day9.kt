package uk.org.fca.adventofcode.y2023.day9

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day9: Day() {
    override fun part1Solution(): BigInteger {
        return this.dayData.sumOf { solveSinglePart1(it) }
    }

    override fun part2Solution(): BigInteger {
        return this.dayData.sumOf { solveSinglePart2(it) }
    }

    fun parseSingle(data: String): List<BigInteger> {
        return data.split(' ').map { BigInteger(it) }
    }

    fun solveSinglePart1(data: String): BigInteger {
        return getNextInSequence(parseSingle(data))
    }

    fun solveSinglePart2(data: String): BigInteger {
        return getFirstInSequence(parseSingle(data))
    }

    fun getNextInSequence(values: List<BigInteger>): BigInteger {
        var lastSteps = values
        val finalValues = mutableListOf<BigInteger>(lastSteps.last())

        while (lastSteps.isNotEmpty() && (lastSteps.first() != BigInteger.ZERO || lastSteps.last() != BigInteger.ZERO)) {
            val steps = mutableListOf<BigInteger>()

            for (i in 1..<lastSteps.size) {
                steps.add(lastSteps[i].minus(lastSteps[i-1]))
            }

            finalValues.add(steps.last())
            lastSteps = steps.toList()
        }

        return finalValues.reduceRight { acc, value -> acc + value }
    }

    fun getFirstInSequence(values: List<BigInteger>): BigInteger {
        var lastSteps = values
        val firstValues = mutableListOf<BigInteger>(lastSteps.first())

        while (lastSteps.isNotEmpty() && (lastSteps.first() != BigInteger.ZERO || lastSteps.last() != BigInteger.ZERO)) {
            val steps = mutableListOf<BigInteger>()

            for (i in 1..<lastSteps.size) {
                steps.add(lastSteps[i].minus(lastSteps[i-1]))
            }

            firstValues.add(steps.first())
            lastSteps = steps.toList()
        }

        return firstValues.reduceRight { acc, value -> acc - value }
    }

    override val day: Int get() = 9
    override val year: Int get() = 2023
}
