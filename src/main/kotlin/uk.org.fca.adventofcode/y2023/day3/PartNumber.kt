package uk.org.fca.adventofcode.y2023.day3

import kotlin.math.max
import kotlin.math.min

data class PartNumber(val startCoord: Coord, val number: Int, val symbol: Symbol) {
    companion object {
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

        fun findPartNumbers(engine: List<CharArray>): List<PartNumber> =
            Symbol.findSymbols(engine).map { findPartNumbersBySymbol(engine, it) }.flatten()

        private fun findPartNumbersBySymbol(engine: List<CharArray>, symbol: Symbol): List<PartNumber> {
            val partNumbers: MutableList<PartNumber> = mutableListOf()
            for (y in max(0, symbol.position.y - 1)..min(engine.size - 1, symbol.position.y + 1)) {
                for (x in max(0, symbol.position.x - 1)..min(engine[y].size - 1, symbol.position.x + 1)) {
                    if (engine[y][x].isDigit()) {
                        partNumbers.add(PartNumber.findPartNumber(engine[y], symbol, Coord(x, y)))
                    }
                }
            }

            return partNumbers.toSet().toList()
        }
    }
}
