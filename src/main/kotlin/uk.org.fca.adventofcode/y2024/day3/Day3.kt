package uk.org.fca.adventofcode.y2024.day3

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day3: Day() {
    override fun part1Solution(): BigInteger =
        BigInteger.valueOf(solvePart1(dayData).toLong())

    override fun part2Solution(): BigInteger =
        BigInteger.valueOf(solvePart2(dayData).toLong())

    override val day: Int get() = 3
    override val year: Int get() = 2024

    companion object {
        fun solvePart1(rawData: List<String>): Int =
            extractMultiplyStatements(rawData, true).sumOf { it.first * it.second }

        fun solvePart2(rawData: List<String>): Int =
            extractMultiplyStatements(rawData).sumOf { it.first * it.second }

        private fun extractStatements(regex: Regex, rawData: List<String>): List<String> =
            regex
                .findAll(rawData.joinToString(""))
                .map { it.groups.first()?.value!! }
                .toList()

        private fun extractMultiplyStatements(
            rawData: List<String>,
            ignoreDoDont: Boolean = false
        ): List<Pair<Int, Int>> {
            var keep = true

            return parseMultiplyStatements(
                extractStatements("""mul\([0-9]{1,3},[0-9]{1,3}\)|do\(\)|don't\(\)""".toRegex(), rawData).filter {
                    var retval = false
                    if (ignoreDoDont) {
                        retval = when (it) {
                            "do()" -> false
                            "don't()" -> false
                            else -> true
                        }
                    } else {
                        when (it) {
                            "do()" -> {
                                keep = true
                            }
                            "don't()" -> {
                                keep = false
                            }
                            else -> {
                                retval = true
                            }
                        }
                    }
                    retval && keep
                }
            )
        }

        private fun parseMultiplyStatements(multiplyStatements: List<String>): List<Pair<Int, Int>> =
            multiplyStatements
                .map { "[0-9]{1,3}".toRegex().findAll(it).map { num -> num.value.toInt() } }
                .map { Pair(it?.first()!!, it.last()) }
    }
}
