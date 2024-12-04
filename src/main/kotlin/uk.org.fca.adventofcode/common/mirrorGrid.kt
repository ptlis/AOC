package uk.org.fca.adventofcode.common

fun mirrorGridVertically(grid: List<String>) = grid.map { it.reversed() }

fun mirrorGridHorizontally(grid: List<String>) = grid.reversed()
