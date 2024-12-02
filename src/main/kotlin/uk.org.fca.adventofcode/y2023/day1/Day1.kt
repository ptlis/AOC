package uk.org.fca.adventofcode.y2023.day1

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day1: Day() {
    override fun part1Solution(): BigInteger {
        return Calibration.parse(dayData, false).values.sum().toBigInteger()
    }

    override fun part2Solution(): BigInteger {
        return Calibration.parse(dayData, true).values.sum().toBigInteger()
    }

    override val day get() = 1
    override val year: Int get() = 2023
}
