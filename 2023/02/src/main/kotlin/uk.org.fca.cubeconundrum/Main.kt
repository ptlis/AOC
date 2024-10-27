package uk.org.fca.cubeconundrum

import java.io.File
import kotlin.math.max

enum class Color {
    RED,
    GREEN,
    BLUE
}

typealias MinimumColours = Map<Color, Int>
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
    val games = File("data/games")
        .useLines { it.toList() }
        .map { parseGameData(it) }

    println("Part 1: ${games.filter { isGamePossible(it) }.sumOf { it.first }}")
    println("Part 2: ${games.sumOf { calculatePower(it) }}")
}

fun calculatePower(game: Game): Int {
    return calculateMinColoursCount(game).values.reduce { acc, next -> acc * next }
}

fun calculateMinColoursCount(game: Game): MinimumColours {
    return game.second
        .flatten()
        .fold(mutableMapOf(Color.RED to 0, Color.BLUE to 0, Color.GREEN to 0)) {
            acc, next ->
                acc[next.first] = max(acc[next.first] ?: 0, + next.second)
                acc
            }
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
