package uk.org.fca.adventofcode.y2024.day6

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.common.Coord
import uk.org.fca.adventofcode.common.Direction
import uk.org.fca.adventofcode.common.Grid
import java.math.BigInteger

class Day6: Day() {
    override fun part1Solution(): BigInteger =
        BigInteger.valueOf(solvePart1(dayData).toLong())

    override fun part2Solution(): BigInteger =
        BigInteger.valueOf(-1)

    override val day: Int get() = 6
    override val year: Int get() = 2024

    companion object {
        fun solvePart1(rawData: List<String>): Int {
            val grid = Grid.parseCharGrid(rawData)

            var currentCoord = grid.findValue('^').first()
            var direction = Direction.NORTH
            val visitedCoords = mutableSetOf<Coord>()

            while (!grid.isOnEdge(currentCoord)) {
                val lastCoord = currentCoord
                currentCoord = move(grid, lastCoord, direction, visitedCoords)
                direction = direction.rotateRight
            }

            return visitedCoords.size
        }

        fun move(
            grid: Grid<Char>,
            startCoord: Coord,
            direction: Direction,
            visitedCoords: MutableSet<Coord>
        ): Coord {
            var currentCoord = startCoord
            do {
                currentCoord += direction.movementStep
                visitedCoords.add(currentCoord)
            } while (grid.exists(currentCoord + direction.movementStep) && grid.get(currentCoord + direction.movementStep) != '#')
            return currentCoord
        }
    }
}
