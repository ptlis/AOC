package uk.org.fca.adventofcode.y2023.day10

import uk.org.fca.adventofcode.Common.Coord
import kotlin.RuntimeException

data class Maze(val grid: List<List<Symbol>>) {
    enum class Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;

        val opposite get(): Direction {
            return mapOf(
                NORTH to SOUTH,
                EAST to WEST,
                SOUTH to NORTH,
                WEST to EAST,
            )[this]!!
        }
    }

    enum class Symbol {
        GROUND,     // `.` is ground; there is no pipe in this tile.
        START,      // `S` is the starting position; there is a pipe of unknown shape here
        VERTICAL,   // `|` is a vertical pipe connecting north and south.
        HORIZONTAL, // `-` is a horizontal pipe connecting east and west.
        NORTH_EAST, // `L` is a 90-degree bend connecting north and east.
        NORTH_WEST, // `J` is a 90-degree bend connecting north and west.
        SOUTH_WEST, // `7` is a 90-degree bend connecting south and west.
        SOUTH_EAST; // `F` is a 90-degree bend connecting south and east.

        val connections get(): Map<Direction, Boolean> {
            return mapOf(
                Direction.NORTH to (this == START || this == VERTICAL || this == NORTH_EAST || this == NORTH_WEST),
                Direction.EAST to (this == START || this == HORIZONTAL || this == SOUTH_EAST || this == NORTH_EAST),
                Direction.SOUTH to (this == START || this == VERTICAL || this == SOUTH_EAST || this == SOUTH_WEST),
                Direction.WEST to (this == START || this == HORIZONTAL || this == SOUTH_WEST || this == NORTH_WEST)
            )
        }
    }

    val startCoord get(): Coord {
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val value = grid[y][x]
                if (value == Symbol.START) {
                    return Coord(x, y)
                }
            }
        }

        throw RuntimeException("Unable to find maze start")
    }

    fun validCoord(coord: Coord): Boolean {
        return coord.y >= 0 && coord.y < grid.size && coord.x >= 0 && coord.x < grid[coord.y].size
    }

    fun get(coord: Coord): Symbol {
        if (!validCoord(coord)) {
            throw RuntimeException("Coord $coord is out of range for this grid")
        }

        return grid[coord.y][coord.x]
    }

    private fun includeAdjacent(source: Coord, direction: Direction): Boolean {
        val sourceSymbol = get(source)
        val possibleAdjacents = mapOf(
            Direction.NORTH to source.north,
            Direction.EAST to source.east,
            Direction.SOUTH to source.south,
            Direction.WEST to source.west,
        )
        val adjacentCoord = possibleAdjacents[direction]!!

        return (
            validCoord(adjacentCoord)
            && sourceSymbol.connections[direction]!!
            && get(adjacentCoord).connections[direction.opposite]!!
        )
    }

    fun findConnectingAdjacents(source: Coord): Pair<Coord, Coord> {
        val adjacents = mapOf(
            Direction.NORTH to source.north,
            Direction.EAST to source.east,
            Direction.SOUTH to source.south,
            Direction.WEST to source.west,
        ).filter { includeAdjacent(source, it.key) }.values.toList()

        if (adjacents.size != 2) {
            throw RuntimeException("Wrong number of adjacents, found $adjacents")
        }

        return Pair(adjacents[0], adjacents[1])
    }

    val stepsFromStartToFurthestPoint get(): Int {
        val startAdjacents = findConnectingAdjacents(this.startCoord)
        val coordsFromSideOne = mutableListOf(this.startCoord, startAdjacents.first)
        val coordsFromSideTwo = mutableListOf(this.startCoord, startAdjacents.second)

        while (!coordsFromSideOne.contains(coordsFromSideTwo.last()) && !coordsFromSideTwo.contains(coordsFromSideOne.last())) {
            coordsFromSideOne.add(findNextStep(coordsFromSideOne))
            coordsFromSideTwo.add(findNextStep(coordsFromSideTwo))
        }

        return coordsFromSideOne.size - 1
    }

    private fun findNextStep(previousCoords: List<Coord>): Coord {
        val adjacents = findConnectingAdjacents(previousCoords.last())

        return if (previousCoords.contains(adjacents.first)) adjacents.second else adjacents.first

    }

    companion object {
        private fun fromChar(symbol: Char): Symbol {
            val symbolMap = mapOf(
                '.' to Symbol.GROUND,
                'S' to Symbol.START,
                '|' to Symbol.VERTICAL,
                '-' to Symbol.HORIZONTAL,
                'L' to Symbol.NORTH_EAST,
                'J' to Symbol.NORTH_WEST,
                '7' to Symbol.SOUTH_WEST,
                'F' to Symbol.SOUTH_EAST
            )

            return symbolMap[symbol] ?: throw RuntimeException("Unknown pipe symbol \"$symbol\" encountered")
        }

        fun parse(rawMazeData: List<String>): Maze {
            return Maze(
                rawMazeData.map { it.toCharArray().map { fromChar(it) } }
            )
        }
    }
}
