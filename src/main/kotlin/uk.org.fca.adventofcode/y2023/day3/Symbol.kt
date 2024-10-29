package uk.org.fca.adventofcode.y2023.day3

data class Symbol(val position: Coord, val symbol: Char) {
    companion object {
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
    }
}
