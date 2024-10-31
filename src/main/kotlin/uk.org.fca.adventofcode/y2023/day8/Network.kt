package uk.org.fca.adventofcode.y2023.day8

import java.lang.RuntimeException

data class Network(val nodes: Map<String, Node>) {
    data class Node(val id: String, val leftId: String, val rightId: String) {
        fun getNextId(instruction: Char): String = when (instruction) {
            'L' -> leftId
            'R' -> rightId
            else -> throw RuntimeException("Only 'L' or 'R' are valid instructions")
        }

        companion object {
            fun parse(rawNode: String): Node {
                val (id, rawTargetNodes) = rawNode.split(" = (")
                val (leftId, rawRightId) = rawTargetNodes.split(", ")
                return Node(id, leftId, rawRightId.dropLast(1))
            }
        }
    }

    companion object {
        fun parse(rawNetwork: List<String>): Network =
            Network(rawNetwork.map { Node.parse(it) }.associateBy { it.id })
    }
}
