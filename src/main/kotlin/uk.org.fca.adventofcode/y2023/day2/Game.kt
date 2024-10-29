package uk.org.fca.adventofcode.y2023.day2

import kotlin.math.max

data class Game(val number: Int, val draws: List<Draw>) {
    fun isPossible(limits: Map<ColourCount.Colour, Int>) = draws.all { it.isPossible(limits) }

    val power get() = minimumColoursCount
        .values
        .reduce { acc, next -> acc * next }

    val minimumColoursCount get() = draws.map { it.colourCounts }
        .flatten()
        .fold(mutableMapOf(ColourCount.Colour.RED to 0, ColourCount.Colour.BLUE to 0, ColourCount.Colour.GREEN to 0)) {
                acc, next ->
            acc[next.colour] = max(acc[next.colour] ?: 0, + next.count)
            acc
        }

    companion object {
        fun parse(data: String): Game {
            val (game, drawsData) = data.split(":")
            return Game(game.split(" ").last().toInt(), Draw.parseDraws(drawsData))
        }
    }
}
