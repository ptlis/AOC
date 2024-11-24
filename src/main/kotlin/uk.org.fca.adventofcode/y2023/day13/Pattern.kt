package uk.org.fca.adventofcode.y2023.day13

import kotlin.math.max

class Pattern(val puzzleLines: List<String>) {
    val horizontalMirror get(): Int {
        return findHorizontalMirror(puzzleLines)
    }
    val verticalMirror get(): Int {
        val rotated = rotated()
        return findHorizontalMirror(rotated, 100)
    }

    val summarization get(): Int {
        return max(0, horizontalMirror) + max(0, verticalMirror)
    }

    private fun findHorizontalMirror(lines: List<String>, multiplier: Int = 1): Int {
        val mirrorPoints: List<List<Int>> = lines.map {
            val innerMirrorPoints = mutableListOf<Int>()
            for (i in 1..<it.length) {
                val left = it.substring(0, i)
                val right = it.substring(i).reversed()

                if (left.endsWith(right)) {
                    innerMirrorPoints.add(i * multiplier)
                }
            }
            innerMirrorPoints
        }

        val validMirrorPoints = mirrorPoints.flatten().distinct().filter {
            possiblePoint -> mirrorPoints.filter { it.contains(possiblePoint) }.size == mirrorPoints.size
        }

        if (validMirrorPoints.isNotEmpty()) {
            return validMirrorPoints.first()
        }

        return -1
    }

    fun rotated(): List<String> {
        val rotated: MutableList<MutableList<Char>> = mutableListOf()

        for (x in puzzleLines[0].indices) {
            rotated.add(mutableListOf())
        }

        for (y in puzzleLines.indices) {
            val lineArray = puzzleLines[y].toCharArray()
            for (x in lineArray.indices) {
                rotated[x].add(lineArray[x])
            }
        }

        return rotated.map { it.joinToString("") }.toList()
    }

    companion object {
        fun parse(data: String): List<Pattern> =data.split("\n\n").map { it.lines() }.map { Pattern(it) }
    }
}