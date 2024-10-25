package uk.org.fca.trebuchet

import java.io.File
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

fun main () {
    println(calibrate(File("data/calibration").useLines { it.toList() }))
}

fun calibrate(calibrationData: List<String>): Int
    = calibrationData.map { getDigits(it) }.sum()

fun getDigits(calibrationLine: String): Int {
    val firstDigit = findFirstDigit(calibrationLine, digitMap)
    val lastDigit = findFirstDigit(calibrationLine.reversed(), digitMap.mapKeys { it.key.reversed() })
    return "$firstDigit$lastDigit".toInt()
}

fun findFirstDigit(calibrationLine: String, digitMap: Map<String, Int>): Int? {
    for (i in calibrationLine.indices) {
        if (calibrationLine[i].isDigit()) {
            return calibrationLine[i].digitToInt()
        }

        digitMap.keys.forEach {
            if (calibrationLine.substring(i, min(i + it.length, calibrationLine.length)) == it) {
                return digitMap[it]
            }
        }
    }
    return null
}
