package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day8.NavigationInstructions
import java.math.BigInteger

class Day8Test: FunSpec({
    context("Part 1") {
        test("example 1") {
            val navigationInstructions = NavigationInstructions.parse("""
                RL
    
                AAA = (BBB, CCC)
                BBB = (DDD, EEE)
                CCC = (ZZZ, GGG)
                DDD = (DDD, DDD)
                EEE = (EEE, EEE)
                GGG = (GGG, GGG)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent().lines())

            navigationInstructions.navigate() shouldBeEqual BigInteger.valueOf(2)
        }

        test("test example 2") {
            val navigationInstructions = NavigationInstructions.parse("""
                LLR

                AAA = (BBB, BBB)
                BBB = (AAA, ZZZ)
                ZZZ = (ZZZ, ZZZ)
            """.trimIndent().lines())

            navigationInstructions.navigate() shouldBeEqual BigInteger.valueOf(6)
        }
    }

    context("Part 2") {
        test("test example") {
            val navigationInstructions = NavigationInstructions.parse("""
                LR

                11A = (11B, XXX)
                11B = (XXX, 11Z)
                11Z = (11B, XXX)
                22A = (22B, XXX)
                22B = (22C, 22C)
                22C = (22Z, 22Z)
                22Z = (22B, 22B)
                XXX = (XXX, XXX)
            """.trimIndent().lines())

            navigationInstructions.ghostNavigate() shouldBeEqual BigInteger.valueOf(6)
        }
    }
})
