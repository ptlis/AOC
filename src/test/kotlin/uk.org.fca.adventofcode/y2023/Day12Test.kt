package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day12.ConditionRecord

class Day12Test: FunSpec({
    context("Part 1") {
        test("unknownCount") {
            ConditionRecord.parse(".??..??...?##. 1,1,3").unknownCount shouldBeEqual 5
        }

        test("possibleMissingConfigurations") {
            ConditionRecord.parse(".??# 1").possibleMissingConfigurations shouldBeEqual 4
        }

        test("isValidConfiguration (valid)") {
            ConditionRecord.isValidConfiguration(
                listOf(1, 1, 2),
                listOf(false, true, false, true, true, false, false, true)
            ) shouldBeEqual true
        }

        test("isValidConfiguration (invalid)") {
            ConditionRecord.isValidConfiguration(
                listOf(1, 1, 2),
                listOf(false, true, false, true, true, false, true, true)
            ) shouldBeEqual false
        }

        context("validDamagedSpringArrangementsCount") {
            withData(
                mapOf(
                    "Example 1 Line 1" to Pair("???.### 1,1,3", 1),
                    "Example 1 Line 2" to Pair(".??..??...?##. 1,1,3", 4),
                    "Example 1 Line 3" to Pair("?#?#?#?#?#?#?#? 1,3,1,6", 1),
                    "Example 1 Line 4" to Pair("????.#...#... 4,1,1", 1),
                    "Example 1 Line 5" to Pair("????.######..#####. 1,6,5", 4),
                    "Example 1 Line 6" to Pair("?###???????? 3,2,1", 10)
                )
            ) {
                ConditionRecord.parse(it.first).validDamagedSpringArrangementsCount shouldBeEqual it.second
            }
        }
    }
})