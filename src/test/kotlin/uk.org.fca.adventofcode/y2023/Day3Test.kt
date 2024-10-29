package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day3.Cog
import uk.org.fca.adventofcode.y2023.day3.Coord
import uk.org.fca.adventofcode.y2023.day3.PartNumber
import uk.org.fca.adventofcode.y2023.day3.Symbol

class Day3Test: FunSpec({
    val testEngine = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent().lines().map { it.toCharArray() }

    context("Part 1") {
        test("Find part numbers") {
            PartNumber.findPartNumbers(testEngine) shouldBeEqual listOf(
                PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(6, 2), 633, Symbol(Coord(6, 3), '#')),
                PartNumber(Coord(0, 4), 617, Symbol(Coord(3, 4), '*')),
                PartNumber(Coord(2, 6), 592, Symbol(Coord(5, 5), '+')),
                PartNumber(Coord(1, 9), 664, Symbol(Coord(3, 8), '$')),
                PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
                PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
            )
        }

        test("Calculate sum of part numbers") {
            PartNumber.findPartNumbers(testEngine).sumOf { it.number } shouldBeEqual 4361
        }
    }

    context("Part 2") {
        test("Find cogs") {
            val partNumbers = listOf(
                PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(6, 2), 633, Symbol(Coord(6, 3), '#')),
                PartNumber(Coord(0, 4), 617, Symbol(Coord(3, 4), '*')),
                PartNumber(Coord(2, 6), 592, Symbol(Coord(5, 5), '+')),
                PartNumber(Coord(1, 9), 664, Symbol(Coord(3, 8), '$')),
                PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
                PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
            )
            val cogs = listOf(
                Cog(listOf(
                    PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
                    PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
                )),
                Cog(listOf(
                    PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
                    PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
                ))
            )

            Cog.findCogs(partNumbers) shouldBeEqual cogs
        }

        test("Calculate cog ratio") {
            val cog = Cog(listOf(
                PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
            ))
            cog.ratio shouldBeEqual 16345
        }

        test("Calculate sum of cog ratios") {
            val cogs = Cog.findCogs(listOf(
                PartNumber(Coord(0, 0), 467, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(2, 2), 35, Symbol(Coord(3, 1), '*')),
                PartNumber(Coord(6, 2), 633, Symbol(Coord(6, 3), '#')),
                PartNumber(Coord(0, 4), 617, Symbol(Coord(3, 4), '*')),
                PartNumber(Coord(2, 6), 592, Symbol(Coord(5, 5), '+')),
                PartNumber(Coord(1, 9), 664, Symbol(Coord(3, 8), '$')),
                PartNumber(Coord(6, 7), 755, Symbol(Coord(5, 8), '*')),
                PartNumber(Coord(5, 9), 598, Symbol(Coord(5, 8), '*')),
            ))
            cogs.sumOf { it.ratio } shouldBeEqual 467835
        }
    }
})
