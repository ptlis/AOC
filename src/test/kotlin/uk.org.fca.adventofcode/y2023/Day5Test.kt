package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day5.Almanac
import java.math.BigInteger

class Day5Test: FunSpec({
    val testAlmanac = Almanac.parse("""
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent().lines())

    context("Part 1") {
        test("map seed to soil") {
            testAlmanac.mapValue(BigInteger.valueOf(53), testAlmanac.seedToSoil) shouldBeEqual BigInteger.valueOf(55)
        }

        context("map seeds to location") {
            withData(mapOf(
                "Seed 79 to location 82" to Pair(BigInteger.valueOf(79), BigInteger.valueOf(82)),
                "Seed 14 to location 43" to Pair(BigInteger.valueOf(14), BigInteger.valueOf(43)),
                "Seed 55 to location 86" to Pair(BigInteger.valueOf(55), BigInteger.valueOf(86)),
                "Seed 12 to location 35" to Pair(BigInteger.valueOf(13), BigInteger.valueOf(35)),
            )
            ) {
                values -> testAlmanac.mapSeedToLocation(values.first) shouldBeEqual values.second
            }
        }
    }
})
