package uk.org.fca.cubeconundrum

import java.io.File

enum class Color {
    RED,
    GREEN,
    BLUE
}

typealias ColorCount = Pair<Color, Int>
typealias Draw = List<ColorCount>
typealias Draws = List<Draw>
typealias Game = Pair<Int, List<Draw>>

val colourLimits = mapOf(
    Color.RED to 12,
    Color.GREEN to 13,
    Color.BLUE to 14
)

fun main () {
    println(
        File("data/games")
            .useLines { it.toList() }
            .map { parseGameData(it) }
            .filter { isGamePossible(it) }
            .sumOf { it.first }
    )
}

fun isDrawPossible(draw: Draw): Boolean = !draw.any { it.second > colourLimits[it.first]!! }

fun areDrawsPossible(draws: Draws): Boolean = draws.all {
    isDrawPossible(it)
}

fun isGamePossible(game: Game): Boolean = areDrawsPossible(game.second)

fun parseColorCount(colorCount: String): ColorCount {
    val (count, color) = colorCount.trim().split(" ")
    return Pair(Color.valueOf(color.uppercase()), count.toInt())
}

fun parseDrawColors(gameData: String): Draw = gameData.split(", ").map { parseColorCount(it) }

fun parseDraws(drawsData: String): Draws = drawsData.split("; ").map { parseDrawColors(it) }

fun parseGameData(data: String): Game {
    val (game, drawsData) = data.split(":")
    return Pair(game.split(" ").last().toInt(), parseDraws(drawsData))
}
