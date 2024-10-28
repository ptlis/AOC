package uk.org.fca.adventofcode.`2023`

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import kotlin.math.min

val digitMap = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

class Day1: Day {
    override fun part1Solution(data: List<String>): BigInteger {
        return calibrate(data, false).toBigInteger()
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return calibrate(data, true).toBigInteger()
    }
}

fun calibrate(calibrationData: List<String>, handleStringNumbers: Boolean): Int
        = calibrationData.map { getDigits(it, handleStringNumbers) }.sum()

fun getDigits(calibrationLine: String, handleStringNumbers: Boolean): Int {
    val firstDigit = findFirstDigit(calibrationLine, digitMap, handleStringNumbers)
    val lastDigit = findFirstDigit(calibrationLine.reversed(), digitMap.mapKeys { it.key.reversed() }, handleStringNumbers)
    return "$firstDigit$lastDigit".toInt()
}

fun findFirstDigit(calibrationLine: String, digitMap: Map<String, Int>, handleStringNumbers: Boolean): Int? {
    for (i in calibrationLine.indices) {
        if (calibrationLine[i].isDigit()) {
            return calibrationLine[i].digitToInt()
        }

        if (handleStringNumbers) {
            digitMap.keys.forEach {
                if (calibrationLine.substring(i, min(i + it.length, calibrationLine.length)) == it) {
                    return digitMap[it]
                }
            }
        }
    }
    return null
}