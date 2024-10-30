package uk.org.fca.adventofcode.y2023.day1

import kotlin.math.min

data class Calibration(val values: List<Int>) {
    companion object {
        private val digitMap = mapOf(
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

        fun parse(calibrationData: List<String>, handleStringNumbers: Boolean): Calibration =
            Calibration(calibrationData.map { getDigits(it, handleStringNumbers) })

        private fun getDigits(calibrationLine: String, handleStringNumbers: Boolean): Int {
            val firstDigit = findFirstDigit(calibrationLine, digitMap, handleStringNumbers)
            val lastDigit = findFirstDigit(calibrationLine.reversed(), digitMap.mapKeys { it.key.reversed() }, handleStringNumbers)
            return "$firstDigit$lastDigit".toInt()
        }

        private fun findFirstDigit(calibrationLine: String, digitMap: Map<String, Int>, handleStringNumbers: Boolean): Int? {
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
    }
}
