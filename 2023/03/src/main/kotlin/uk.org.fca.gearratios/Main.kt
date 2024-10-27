package uk.org.fca.gearratios

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main () {
    val engine = File("data/engine")
        .useLines { it.toList() }
        .map { it.toCharArray() }

    println(findPartNumbers(engine).sum())
    println(findPartNumbers(engine).joinToString("\n"))
}

fun findPartNumbers(engine: List<CharArray>): List<Int> {
    var x = 0
    var y = 0
    val partNumbers = mutableListOf<Int>()

    do {
        do {
            if (engine[y][x].isDigit()) {
                val possiblePartNumber = getPossiblePartNumber(engine[y], x)
                if (isPartNumber(engine, x, y, possiblePartNumber)) {
                    partNumbers.add(possiblePartNumber.toInt())
                    x = x + possiblePartNumber.length - 1
                }
            }

            x++
        } while (x < engine[y].size)

        x = 0
        y++
    } while (y < engine.size)

    return partNumbers.toList()
}

fun getPossiblePartNumber(engineLine: CharArray, startIndex: Int): String {
    var partNumber = charArrayOf()
    for (x in startIndex..<engineLine.size) {
        if (!engineLine[x].isDigit()) break
        partNumber += engineLine[x]
    }
    return partNumber.concatToString()
}

fun isPartNumber(engine: List<CharArray>, startX: Int, startY: Int, possiblePartNumber: String): Boolean {
    for (y in max(0, startY - 1)..min(engine.size - 1, startY + 1)) {
        for (x in max(0, startX - 1)..min(engine[y].size - 1, startX + possiblePartNumber.length)) {
            if (!(engine[y][x].isDigit() || engine[y][x] == '.')) {
                return true
            }
        }
    }
    return false
}