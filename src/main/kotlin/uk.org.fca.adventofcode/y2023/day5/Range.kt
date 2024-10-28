package uk.org.fca.adventofcode.y2023.day5

import uk.org.fca.adventofcode.rangeTo
import java.math.BigInteger

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
