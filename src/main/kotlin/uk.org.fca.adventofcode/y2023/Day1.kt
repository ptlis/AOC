package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day1.Calibration
import java.math.BigInteger


class Day1: Day() {
    override fun part1Solution(): BigInteger {
        return Calibration.parse(dayData, false).values.sum().toBigInteger()
    }

    override fun part2Solution(): BigInteger {
        return Calibration.parse(dayData, true).values.sum().toBigInteger()
    }

    override val number get() = 1
}
