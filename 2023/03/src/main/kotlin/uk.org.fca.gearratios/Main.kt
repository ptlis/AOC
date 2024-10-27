package uk.org.fca.gearratios

import java.io.File
import kotlin.math.max
import kotlin.math.min

data class Coord(val x: Int, val y: Int)
data class Symbol(val position: Coord, val symbol: Char)
data class PartNumber(val startCoord: Coord, val number: Int, val symbol: Symbol)
data class Cog(val partNumbers: List<PartNumber>) {
    val ratio
        get() = this.partNumbers.fold(1) { acc, next -> acc * next.number }
}

fun main () {
    val engine = File("data/engine")
        .useLines { it.toList() }
        .map { it.toCharArray() }

    val partNumbers = findPartNumbers(engine)

    println("Part 1: ${partNumbers.sumOf { it.number }}")

    println("Part 2: ${findCogs(partNumbers).sumOf { it.ratio }}")
}

fun findCogs(partNumbers: List<PartNumber>): List<Cog> =
    partNumbers
        .filter { it.symbol.symbol == '*' }
        .groupBy({"${it.symbol.position.x},${it.symbol.position.y}"}) { it }
        .filter { it.value.size > 1 }
        .map { Cog(it.value) }

fun findPartNumbers(engine: List<CharArray>): List<PartNumber> =
    findSymbols(engine).map { findPartNumbersBySymbol(engine, it) }.flatten()

fun findPartNumber(engineLine: CharArray, symbol: Symbol, numberFoundPosition: Coord): PartNumber {
    var startX = numberFoundPosition.x
    var endX = numberFoundPosition.x

    while (startX - 1 >= 0 && engineLine[startX - 1].isDigit()) {
        startX--
    }

    while (endX + 1 < engineLine.size && engineLine[endX + 1].isDigit()) {
        endX++
    }

    return PartNumber(
        Coord(startX, numberFoundPosition.y),
        engineLine.slice(startX..endX).joinToString("").toInt(),
        symbol
    )
}

fun findPartNumbersBySymbol(engine: List<CharArray>, symbol: Symbol): List<PartNumber> {
    val partNumbers: MutableList<PartNumber> = mutableListOf()
    for (y in max(0, symbol.position.y - 1)..min(engine.size - 1, symbol.position.y + 1)) {
        for (x in max(0, symbol.position.x - 1)..min(engine[y].size - 1, symbol.position.x + 1)) {
            if (engine[y][x].isDigit()) {
                partNumbers.add(findPartNumber(engine[y], symbol, Coord(x, y)))
            }
        }
    }

    return partNumbers.toSet().toList()
}

fun findSymbols(engine: List<CharArray>): List<Symbol> {
    val symbols = mutableListOf<Symbol>()
    for (y in engine.indices) {
        for (x in engine[y].indices) {
            if (!engine[y][x].isDigit() && engine[y][x] != '.') {
                symbols.add(Symbol(Coord(x, y), engine[y][x]))
            }
        }
    }
    return symbols.toList()
}