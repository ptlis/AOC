package uk.org.fca.adventofcode.y2023.day8

import java.math.BigInteger

data class NavigationInstructions(val instructions: Instructions, val network: Network) {
    data class Instructions(val instructions: String) {
        private var index = 0

        val next get(): Char {
            val instruction = instructions.get(index)
            index++
            if (index >= instructions.length) {
                reset()
            }
            return instruction
        }

        fun reset() {
            index = 0
        }
    }

    fun navigate(): BigInteger {
        var node = network.nodes["AAA"]
        var count = BigInteger.ZERO

        while (node!!.id != "ZZZ") {
            node = network.nodes[node.getNextId(instructions.next)]
            count++
        }
        return count
    }

    fun ghostNavigate(): BigInteger {
        var nodes = network.nodes.values.filter { it.id.last() == 'A' }
        var count = BigInteger.ZERO

        while (!allAtDestination(nodes)) {
            count++
            val nextInstruction = instructions.next
            nodes = nodes.map { network.nodes[it.getNextId(nextInstruction)]!! }
        }

        return count
    }

    private fun allAtDestination(nodes: List<Network.Node>): Boolean {
        return nodes.filter { it.id.last() == 'Z' }.size == nodes.size
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
