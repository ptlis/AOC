package uk.org.fca.adventofcode.y2023.day8

import java.math.BigInteger

data class NavigationInstructions(val instructions: Instructions, val network: Network) {
    data class Instructions(val instructions: String) {
        private var index = 0

        val next get(): Char {
            val instruction = instructions.get(index)
            index++
            if (index >= instructions.length) {
                index = 0
            }
            return instruction
        }
    }

    fun navigate(startId: String, targetId: String): BigInteger {
        var node = network.nodes[startId]
        var count = BigInteger.ZERO

        while (node!!.id != targetId) {
            node = network.nodes[node.getNextId(instructions.next)]
            count++
        }
        return count
    }

    companion object {
        fun parse(rawNavigationData: List<String>): NavigationInstructions {
            return NavigationInstructions(
                Instructions(rawNavigationData.first()),
                Network.parse(rawNavigationData.drop(2))
            )
        }
    }
}
