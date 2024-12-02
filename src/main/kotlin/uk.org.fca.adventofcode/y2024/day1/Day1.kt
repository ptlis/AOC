package uk.org.fca.adventofcode.y2024.day1

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import kotlin.math.abs

typealias LocationIdsList = Pair<List<Int>, List<Int>>

class Day1: Day() {
    override fun part1Solution(): BigInteger {
        return BigInteger.valueOf(solvePart1(parse(dayData)).toLong())
    }

    override fun part2Solution(): BigInteger {
        return BigInteger.valueOf(solvePart2(parse(dayData)).toLong())
    }

    override val day: Int get() = 1
    override val year: Int get() = 2024

    companion object {
        fun parse(rawData: List<String>): LocationIdsList {
            val locationList1 = mutableListOf<Int>()
            val locationList2 = mutableListOf<Int>()

            rawData.forEach {
                val digits = it.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
                locationList1.add(digits[0])
                locationList2.add(digits[1])
            }

            return Pair(locationList1.toList(), locationList2.toList())
        }

        fun solvePart1(locations: LocationIdsList): Int =
            locations.first.sorted().zip(locations.second.sorted()).sumOf { abs(it.first - it.second) }

        fun solvePart2(locations: LocationIdsList): Int =
            locations.first.filter { locations.second.contains(it) }.sorted().zip(
                locations.second.filter { locations.first.contains(it) }.sorted().groupBy { it }.map { it.value.size }
            ).sumOf { it.first * it.second }
    }
}