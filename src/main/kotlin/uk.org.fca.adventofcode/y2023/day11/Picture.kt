package uk.org.fca.adventofcode.y2023.day11

import uk.org.fca.adventofcode.Common.Coord
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min

data class Picture(val grid: List<List<Char>>, val expansionFactor: Int) {
    val expanded get(): Picture {
        val expandedGrid = grid.map { it.toMutableList() }.toMutableList()

        // Find empty columns (right to left)
        val emptyCols = mutableListOf<Int>()
        for (x in grid[0].indices.reversed()) {
            var isEmpty = true
            for (y in grid.indices) {
                if (grid[y][x] != '.') {
                    isEmpty = false
                }
            }
            if (isEmpty) {
                emptyCols.add(x)
            }
        }

        // Find empty rows (bottom to top)
        val emptyRows = mutableListOf<Int>()
        for (y in grid.indices.reversed()) {
            if (grid[y].none { it != '.' }) {
                emptyRows.add(y)
            }
        }

        // Insert additional columns
        emptyCols.forEach {
            for (y in expandedGrid.indices) {
                expandedGrid[y][it] = '*'
            }
        }

        // Insert additional rows
        emptyRows.forEach {
            expandedGrid[it] = expandedGrid[it].map { '*' }.toMutableList()
        }

        return Picture(expandedGrid.map { it.toList() }.toList(), expansionFactor)
    }

    val shortestGalaxyPaths get(): List<BigInteger> {
        val galaxyCoords = mutableListOf<Coord>()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == '#') {
                    galaxyCoords.add(Coord(x, y))
                }
            }
        }

        val pairs = mutableMapOf<String, Pair<Coord, Coord>>()

        for (i in galaxyCoords.indices) {
            for (j in i..<galaxyCoords.size) {
                val coord1 = galaxyCoords[i]
                var coord2 = galaxyCoords[j]

                if (coord1.x < coord2.x || coord1.y < coord2.y) {
                    pairs["$coord1-$coord2"] = Pair(coord1, coord2)
                }
            }
        }

        return pairs.values.map {
            var expansions = 0;

            // Calculate how many expansions are between the galaxies
            for (x in min(it.first.x, it.second.x)..max(it.first.x, it.second.x)) {
                if (grid[it.first.y][x] == '*') {
                    expansions++
                }
            }

            for (y in min(it.first.y, it.second.y)..max(it.first.y, it.second.y)) {
                if (grid[y][it.first.x] == '*') {
                    expansions++
                }
            }

            (
                BigInteger.valueOf(it.first.distanceTo(it.second).toLong())
                + (BigInteger.valueOf(expansions.toLong()) * BigInteger.valueOf(expansionFactor.toLong()))
                - if (expansionFactor == 1) BigInteger.ZERO else BigInteger.valueOf(expansions.toLong())
            )
        }
    }

    override fun toString(): String = grid.joinToString("\n") { it.joinToString("") }

    companion object {
        fun parse(rawPictureData: List<String>, expansionFactor: Int): Picture =
             Picture(rawPictureData.map { it.toCharArray().toList() }, expansionFactor)
    }
}
