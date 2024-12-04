package uk.org.fca.adventofcode.common

fun rotateStringGrid(grid: List<String>): List<String> {
    val rotated: MutableList<MutableList<Char>> = mutableListOf()

    for (x in grid[0].indices) {
        rotated.add(mutableListOf())
    }

    for (y in grid.indices) {
        val lineArray = grid[y].toCharArray()
        for (x in lineArray.indices) {
            rotated[x].add(lineArray[x])
        }
    }

    return rotated.map { it.joinToString("") }.toList()
}
