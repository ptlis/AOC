package uk.org.fca.adventofcode.y2023.day12

import uk.org.fca.adventofcode.common.runLengthEncode

data class ConditionRecord(val springData: List<SpringState>, val damagedSpringData: List<Int>) {

    enum class SpringState {
        OPERATIONAL,
        DAMAGED,
        UNKNOWN
    }

    val unknownCount get() = springData.filter { it == SpringState.UNKNOWN }.size

    // Each spring must be either operational or damaged so we can think of this in binary terms - use an int to
    //  represent all possibilities
    val possibleMissingConfigurations get() = 1 shl unknownCount

    val validDamagedSpringArrangementsCount get(): Int {
        var validArrangementsCount = 0;
        val maxNumDamagedSprings = damagedSpringData.sum()
        for (i in 0 ..< possibleMissingConfigurations) {
            val possibleMissingConfiguration = Integer.toBinaryString(i)
                .padStart(unknownCount, '0')
                .toCharArray()
                .map { it == '1' }
                .toMutableList()
            if (possibleMissingConfiguration.filter { !it }.size <= maxNumDamagedSprings) {
                val repairedRecord: List<Boolean> = springData.map {
                    when (it) {
                        SpringState.UNKNOWN -> possibleMissingConfiguration.removeFirst()
                        SpringState.DAMAGED -> false
                        SpringState.OPERATIONAL -> true
                    }
                }

                // Confirm repairedRecord is valid
                if (isValidConfiguration(this.damagedSpringData, repairedRecord)) {
                    validArrangementsCount++;
                }
            }
        }

        return validArrangementsCount
    }

    companion object {
        fun parse(rawConditionRecord: String): ConditionRecord {
            val (rawSpringData, rawDamagedSpringData) = rawConditionRecord.split(" ")
            return ConditionRecord(
                rawSpringData.toCharArray().map { when(it) {
                    '.' -> SpringState.OPERATIONAL
                    '#' -> SpringState.DAMAGED
                    '?' -> SpringState.UNKNOWN
                    else -> throw IllegalArgumentException("The provided spring condition is invalid")
                } },
                rawDamagedSpringData.split(",").map { it.toInt() }
            )
        }

        fun isValidConfiguration(damagedSpringData: List<Int>, springsConfiguration: List<Boolean>): Boolean {
            val rleConfiguration = runLengthEncode(springsConfiguration)
    
            return rleConfiguration.filter { !it.first }.map { it.second } == damagedSpringData
        }
    }
}