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

    private val startCoord get(): Coord {
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

    private fun validCoord(coord: Coord): Boolean {
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

    fun findConnectingAdjacents(source: Coord): Map<Direction, Coord> {
        val adjacents = mapOf(
            Direction.NORTH to source.north,
            Direction.EAST to source.east,
            Direction.SOUTH to source.south,
            Direction.WEST to source.west,
        ).filter { includeAdjacent(source, it.key) }

        if (adjacents.size != 2) {
            throw RuntimeException("Wrong number of adjacents, found $adjacents")
        }

        return adjacents
    }

    fun getCoordsInPathLoop(): Set<Coord> {
        val startAdjacents = findConnectingAdjacents(this.startCoord).values.toList()
        val coordsFromSideOne = mutableListOf(this.startCoord, startAdjacents[0])
        val coordsFromSideTwo = mutableListOf(this.startCoord, startAdjacents[1])

        while (!coordsFromSideOne.contains(coordsFromSideTwo.last()) && !coordsFromSideTwo.contains(coordsFromSideOne.last())) {
            coordsFromSideOne.add(findNextStep(coordsFromSideOne))
            coordsFromSideTwo.add(findNextStep(coordsFromSideTwo))
        }

        return (coordsFromSideOne + coordsFromSideTwo).toSet()
    }

    val stepsFromStartToFurthestPoint get(): Int {
        val startAdjacents = findConnectingAdjacents(this.startCoord).values.toList()
        val coordsFromSideOne = mutableListOf(this.startCoord, startAdjacents[0])
        val coordsFromSideTwo = mutableListOf(this.startCoord, startAdjacents[1])

        while (!coordsFromSideOne.contains(coordsFromSideTwo.last()) && !coordsFromSideTwo.contains(coordsFromSideOne.last())) {
            coordsFromSideOne.add(findNextStep(coordsFromSideOne))
            coordsFromSideTwo.add(findNextStep(coordsFromSideTwo))
        }

        return coordsFromSideOne.size - 1
    }

    private fun findNextStep(previousCoords: List<Coord>): Coord {
        val adjacents = findConnectingAdjacents(previousCoords.last()).values.toList()

        return if (previousCoords.contains(adjacents[0])) adjacents[1] else adjacents[0]
    }

    val insideCoords get(): List<Coord> {
        // Create grid 3x the size of pipe grid & map pipe shapes directly onto this
        val insideOutsideGrid = MutableList(grid.size * 3) { MutableList(grid[0].size * 3) { ' ' } }
        val loopCoords = getCoordsInPathLoop()

        loopCoords.forEach {
            val insideOutsideY = 3 * it.y
            val insideOutsideX = 3 * it.x

            when (grid[it.y][it.x]) {
                Symbol.GROUND -> {
                    // Do nothing for ground, this path will never be called
                }

                Symbol.START -> {
                    val adjacents = findConnectingAdjacents(Coord(it.x, it.y))

                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    if (adjacents[Direction.NORTH] != null) {
                        insideOutsideGrid[insideOutsideY + 0][insideOutsideX + 1] = 'p'
                    }

                    if (adjacents[Direction.EAST] != null) {
                        insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 2] = 'p'
                    }

                    if (adjacents[Direction.SOUTH] != null) {
                        insideOutsideGrid[insideOutsideY + 2][insideOutsideX + 1] = 'p'
                    }

                    if (adjacents[Direction.WEST] != null) {
                        insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 0] = 'p'
                    }
                }

                Symbol.VERTICAL -> {
                    insideOutsideGrid[insideOutsideY + 0][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 2][insideOutsideX + 1] = 'p'
                }

                Symbol.HORIZONTAL -> {
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 0] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 2] = 'p'
                }

                Symbol.NORTH_EAST -> {
                    insideOutsideGrid[insideOutsideY + 0][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 2] = 'p'
                }

                Symbol.NORTH_WEST -> {
                    insideOutsideGrid[insideOutsideY + 0][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 0] = 'p'
                }

                Symbol.SOUTH_WEST -> {
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 0] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 2][insideOutsideX + 1] = 'p'
                }

                Symbol.SOUTH_EAST -> {
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 1] = 'p'
                    insideOutsideGrid[insideOutsideY + 1][insideOutsideX + 2] = 'p'
                    insideOutsideGrid[insideOutsideY + 2][insideOutsideX + 1] = 'p'
                }
            }
        }

        val fillStart = outsideCoord
        if (fillStart != null) {
            floodFill(insideOutsideGrid, Coord(fillStart.x * 3, fillStart.y * 3))
        } else {
            throw NotImplementedError("Ohno, looks like I need to implement a mechanism to find an inside coord instead")
        }

        val insideCoords = mutableListOf<Coord>()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (insideOutsideGrid[(y * 3) + 1][(x * 3) + 1] == ' ') {
                    insideCoords.add(Coord(x, y))
                }
            }
        }

        return insideCoords
    }

    private fun floodFill(
        insideOutsideGrid: MutableList<MutableList<Char>>,
        startCoord: Coord
    ) {
        val stack = ArrayDeque<Coord>()
        stack.addFirst(startCoord)

        while (stack.isNotEmpty()) {
            val currentCoord = stack.removeFirst()

            if (
                insideOutsideGrid[currentCoord.y][currentCoord.x] == ' '
                ) {
                insideOutsideGrid[currentCoord.y][currentCoord.x] = '.'
                if (
                    currentCoord.north.y >= 0 && currentCoord.north.y < insideOutsideGrid.size
                    && currentCoord.north.x >= 0 && currentCoord.north.x < insideOutsideGrid[currentCoord.north.y].size
                ) {
                    stack.addFirst(currentCoord.north)
                }
                if (
                    currentCoord.east.y >= 0 && currentCoord.east.y < insideOutsideGrid.size
                    && currentCoord.east.x >= 0 && currentCoord.east.x < insideOutsideGrid[currentCoord.east.y].size
                ) {
                    stack.addFirst(currentCoord.east)
                }
                if (
                    currentCoord.south.y >= 0 && currentCoord.south.y < insideOutsideGrid.size
                    && currentCoord.south.x >= 0 && currentCoord.south.x < insideOutsideGrid[currentCoord.south.y].size
                ) {
                    stack.addFirst(currentCoord.south)
                }
                if (
                    currentCoord.west.y >= 0 && currentCoord.west.y < insideOutsideGrid.size
                    && currentCoord.west.x >= 0 && currentCoord.west.x < insideOutsideGrid[currentCoord.west.y].size
                ) {
                    stack.addFirst(currentCoord.west)
                }
            }
        }
    }

    /**
     * Find a coord outside the loop (by moving around the edges until it reaches a pipe), returns null if no outside
     *  coord was found
     */
    private val outsideCoord get(): Coord? {
        // Top & bottom edges
        for (x in 0..<grid[0].size) {
            if (grid[0][x] == Symbol.GROUND) {
                return Coord(x, 0)
            }
            if (grid[grid.size - 1][x] == Symbol.GROUND) {
                return Coord(x, grid.size - 1)
            }
        }

        // Left & right edges
        for (y in 1..<grid.size - 1) {
            if (grid[y][0] == Symbol.GROUND) {
                return Coord(0, y)
            }
            if (grid[y][grid[y].size - 1] == Symbol.GROUND) {
                return Coord(grid[y].size - 1, y)
            }
        }

        return null
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
