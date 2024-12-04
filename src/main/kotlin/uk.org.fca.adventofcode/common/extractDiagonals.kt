package uk.org.fca.adventofcode.common

/**
 * Extracts all diagonals from a 'string grid'
 */
fun extractDiagonals(grid: List<String>): List<String> {
    val newLines = mutableListOf<String>()
    for (k in 0..2 * (grid.size - 1)) {
        val line = mutableListOf<Char>()
        for (y in grid.indices) {
            val x = k - y
            if (x >= 0 && x < grid.size) {
                line.add(grid[y][x])
            }
        }
        newLines.add(line.joinToString(""))
    }
    return newLines
}
