package uk.org.fca.adventofcode.y2023.day9

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day9: Day() {
    override fun part1Solution(): BigInteger {
        return this.dayData.sumOf { solveSingle(it) }
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(-1)
    }

    fun parseSingle(data: String): List<BigInteger> {
        return data.split(' ').map { BigInteger(it) }
    }

    fun solveSingle(data: String): BigInteger {
        return getSteps(parseSingle(data))
    }

    fun getSteps(values: List<BigInteger>): BigInteger {
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

    override val number: Int get() = 9
}
