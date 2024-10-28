package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.y2023.day5.Almanac
import java.math.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {
    private val testAlmanac = Almanac.parse("""
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

    @Test
    fun testMapSeedToSoil() {
        val input = BigInteger.valueOf(53)
        val expected = BigInteger.valueOf(55)

        assertEquals(expected, testAlmanac.mapValue(input, testAlmanac.seedToSoil))
    }

    @Test
    fun testPart1Example1() {
        val input = BigInteger.valueOf(79)
        val expected = BigInteger.valueOf(82)

        assertEquals(expected, testAlmanac.mapSeedToLocation(input))
    }

    @Test
    fun testPart1Example2() {
        val input = BigInteger.valueOf(14)
        val expected = BigInteger.valueOf(43)

        assertEquals(expected, testAlmanac.mapSeedToLocation(input))
    }

    @Test
    fun testPart1Example3() {
        val input = BigInteger.valueOf(55)
        val expected = BigInteger.valueOf(86)

        assertEquals(expected, testAlmanac.mapSeedToLocation(input))
    }

    @Test
    fun testPart1Example4() {
        val input = BigInteger.valueOf(13)
        val expected = BigInteger.valueOf(35)

        assertEquals(expected, testAlmanac.mapSeedToLocation(input))
    }

//    @Test
//    fun testPart2Example1() {
//        val expected = BigInteger.valueOf(35)
//
//        assertEquals(expected, testAlmanac.mapSeedRangeToSmallestLocation(BigInteger.valueOf(79), BigInteger.valueOf(14)))
//    }
}
