package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import java.math.BigInteger
import uk.org.fca.adventofcode.rangeTo

data class Almanac(
    val seeds: List<BigInteger>,
    val seedToSoil: List<Range>,
    val soilToFertilizer: List<Range>,
    val fertilizerToWater: List<Range>,
    val waterToLight: List<Range>,
    val lightToTemperature: List<Range>,
    val temperatureToHumidity: List<Range>,
    val humidityToLocation: List<Range>
) {
    val seedRanges
        get() = seeds.chunked(2)

    companion object {
        fun parse(data: List<String>): Almanac {
            val mutableData = data.toMutableList()

            // Read seeds
            val seeds = mutableData.removeFirst().removePrefix("seeds: ").split(" ").map { it.toBigInteger() }
            mutableData.removeFirst() // Skip empty line

            val seedToSoil = parseRangeBlock(mutableData)
            mutableData.removeFirst() // Skip empty line

            val soilToFertilizer = parseRangeBlock(mutableData)
            mutableData.removeFirst() // Skip empty line

            val fertilizerToWater = parseRangeBlock(mutableData)
            mutableData.removeFirst() // Skip empty line

            val waterToLight = parseRangeBlock(mutableData)
            mutableData.removeFirst() // Skip empty line

            val lightToTemperature = parseRangeBlock(mutableData)
            mutableData.removeFirst() // Skip empty line

            val temperatureToHumidity = parseRangeBlock(mutableData)
            mutableData.removeFirst() // Skip empty line

            val humidityToLocation = parseRangeBlock(mutableData)

            return Almanac(
                seeds,
                seedToSoil,
                soilToFertilizer,
                fertilizerToWater,
                waterToLight,
                lightToTemperature,
                temperatureToHumidity,
                humidityToLocation
            )
        }

        private fun parseRangeBlock(mutableData: MutableList<String>): List<Range> {
            // Skip header
            mutableData.removeFirst()
            val extractedData = mutableListOf<String>()

            while (mutableData.isNotEmpty() && mutableData[0] != "") {
                extractedData.add(mutableData.removeFirst())
            }

            return extractedData.map { Range.parse(it) }
        }
    }

    fun mapValue(value: BigInteger, mapper: List<Range>): BigInteger {
        val appliedRange = mapper.filter { it.isInSourceRange(value) }

        return if (appliedRange.isNotEmpty()) appliedRange[0].mapValue(value) else value
    }

    fun mapSeedToLocation(seed: BigInteger): BigInteger {
        val soil = mapValue(seed, seedToSoil)
        val fertilizer = mapValue(soil, soilToFertilizer)
        val water = mapValue(fertilizer, fertilizerToWater)
        val light = mapValue(water, waterToLight)
        val temperature = mapValue(light, lightToTemperature)
        val humidity = mapValue(temperature, temperatureToHumidity)
        return mapValue(humidity, humidityToLocation)
    }

    fun mapRangeToSmallestValue(start: BigInteger, length: BigInteger, mapper: List<Range>): Range {
        return seedToSoil.filter { start in it.sourceStart..it.sourceEnd }.first()
    }

    fun mapSeedRangeToSmallestLocation(seedStart: BigInteger, length: BigInteger): BigInteger {

        val soilRange = mapRangeToSmallestValue(seedStart, length, seedToSoil)



        val fertilizerRange = mapRangeToSmallestValue(soilRange.sourceStart, soilRange.size, soilToFertilizer)
        val waterRange = mapRangeToSmallestValue(fertilizerRange.sourceStart, fertilizerRange.size, fertilizerToWater)
        val lightRange = mapRangeToSmallestValue(waterRange.sourceStart, waterRange.size, waterToLight)
        val temperatureRange = mapRangeToSmallestValue(lightRange.sourceStart, lightRange.size, lightToTemperature)
        val humidityRange = mapRangeToSmallestValue(temperatureRange.sourceStart, temperatureRange.size, temperatureToHumidity)
        return mapRangeToSmallestValue(humidityRange.sourceStart, humidityRange.size, humidityToLocation).destinationStart
    }
}

data class Range(val destinationStart: BigInteger, val sourceStart: BigInteger, val size: BigInteger) {
    val sourceEnd get() = sourceStart.add(size).minus(BigInteger.valueOf(1))

    companion object {
        fun parse(rawRangeData: String): Range {
            val parts = rawRangeData.split(" ").map { it.toBigInteger() }
            return Range(parts[0], parts[1], parts[2])
        }
    }

    fun isInSourceRange(value: BigInteger): Boolean {
        return value in sourceStart..sourceEnd
    }

    fun mapValue(value: BigInteger): BigInteger {
        return destinationStart + (value - sourceStart)
    }
}

class Day5: Day {
    override fun part1Solution(data: List<String>): BigInteger {
        val almanac = Almanac.parse(data)

        return almanac.seeds.map { almanac.mapSeedToLocation(it) }.min()
    }

    override fun part2Solution(data: List<String>): BigInteger {
        return BigInteger.valueOf(-1)
    }
}
