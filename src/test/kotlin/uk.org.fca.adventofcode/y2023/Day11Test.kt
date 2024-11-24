package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.Coord
import uk.org.fca.adventofcode.y2023.day11.Picture
import java.math.BigInteger

class Day11Test: FunSpec({
    val exampleGalaxy = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....""".trimIndent().lines()

    context("Part 1") {
        test("Test Expand Algorithm") {
            val picture = Picture.parse(exampleGalaxy, 2)

            picture.expanded.toString() shouldBeEqual """
                ..*#.*..*.
                ..*..*.#*.
                #.*..*..*.
                **********
                ..*..*#.*.
                .#*..*..*.
                ..*..*..*#
                **********
                ..*..*.#*.
                #.*.#*..*.
            """.trimIndent()
        }

        context("Coord.distanceTo") {
            withData(
                mapOf(
                    "Example 1" to Pair(Pair(Coord(1, 6), Coord(5, 11)), 9),
                    "Example 2" to Pair(Pair(Coord(0, 4), Coord(9, 10)), 15),
                    "Example 3" to Pair(Pair(Coord(0, 2), Coord(12, 7)), 17),
                    "Example 4" to Pair(Pair(Coord(0, 11), Coord(5, 11)), 5)
                )
            ) {
                it.first.first.distanceTo(it.first.second) shouldBeEqual it.second
            }
        }

        test("Test calculate sum of shortest galaxy paths") {
            val picture = Picture.parse(exampleGalaxy, 2)

            picture.expanded.shortestGalaxyPaths.sumOf { it } shouldBeEqual BigInteger.valueOf(374)
        }
    }

    context("Part 2") {
        test("Test calculate sum of shortest galaxy paths (distance: 10)") {
            val picture = Picture.parse(exampleGalaxy, 10)

            picture.expanded.shortestGalaxyPaths.sumOf { it } shouldBeEqual BigInteger.valueOf(1030)
        }

        test("Test calculate sum of shortest galaxy paths (distance: 100)") {
            val picture = Picture.parse(exampleGalaxy, 100)

            picture.expanded.shortestGalaxyPaths.sumOf { it } shouldBeEqual BigInteger.valueOf(8410)
        }
    }
})
