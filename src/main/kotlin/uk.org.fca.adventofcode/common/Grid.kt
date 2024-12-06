package uk.org.fca.adventofcode.common

class Grid<T>(private val gridData: List<List<T>>) {
    fun findValue(value: T): List<Coord> {
        val matches = mutableListOf<Coord>()
        for (y in gridData.indices) {
            for (x in gridData[y].indices) {
                if (gridData[y][x] == value) {
                    matches.add(Coord(x, y))
                }
            }
        }
        return matches
    }

    fun get(coord: Coord): T {
        if (!exists(coord)) {
            throw RuntimeException("Unable to read from invalid coordinate $coord")
        }

        return gridData[coord.y][coord.x]
    }

    val width get(): Int = gridData[0].size
    val height get(): Int = gridData.size

    fun exists(coord: Coord): Boolean =
        coord.y in 0..<width && coord.x in 0..<height

    fun isOnEdge(coord: Coord): Boolean =
        coord.y == 0 || coord.y == height - 1 || coord.x == 0 || coord.x == width - 1

    override fun equals(other: Any?): Boolean =
        other is Grid<*> && other.gridData == this.gridData

    override fun hashCode(): Int =
        gridData.hashCode()

    companion object {
        fun parseIntGrid(gridLines: List<String>): Grid<Int> =
            Grid(gridLines.map { line ->
                line.toCharArray().map { it.toString().toInt() }
            })

        fun parseCharGrid(gridLines: List<String>): Grid<Char> =
            Grid(gridLines.map { it.toCharArray().toList() })
    }
}
