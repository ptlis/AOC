package uk.org.fca.adventofcode.y2023.day8

import java.lang.RuntimeException
import java.math.BigInteger

data class NavigationInstructions(val instructions: Instructions, val network: Network) {
    data class SteppedInstructions(val wrappedInstructions: Instructions) {
        fun step(value: Int): Char {
            if (value == 0) {
                throw RuntimeException("Step of 0 is not allowed")
            }

            val char = wrappedInstructions.next

            for (i in 1..<value) {
                wrappedInstructions.next
            }

            return char
        }
    }

    data class Instructions(val instructions: String) {
        private var index = 0

        val next get(): Char {
            val instruction = instructions[index]
            index++
            if (index >= instructions.length) {
                index = 0
            }
            return instruction
        }


        private fun setIndex(index: Int): Instructions {
            this.index = index

            if (this.index >= instructions.length) {
                this.index = this.index % instructions.length
            }
            return this
        }

        fun clone(startIndex: Int): Instructions = Instructions(instructions).setIndex(startIndex)
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

        nodes.mapIndexed() { index, node ->
            val nodeInstructions = SteppedInstructions(instructions.clone(index))
            var currentId = node.id
            var count = 1

            while (currentId.last() != 'Z') {
                currentId = node.getNextId(nodeInstructions.step(nodes.size))
                count++
                println("$index $currentId")
            }
        }

        return BigInteger.valueOf(-1)

        // Target equivalent
//        1 (0): 11A
//        3 (0): 11B
//        5 (0): 11Z
//        7 (0): 11B
//        9 (0): 11Z
//        11 (0): 11B
//        13 (0): 11Z

//        2 (1): 22A
//        4 (1): 22B
//        6 (1): 22C
//        8 (1): 22Z
//        10 (1): 22B
//        12 (1): 22C
//        14 (1): 22Z
//
//        // TODO: Make this work; should step through until 'Z' is reached and count the steps
//        nodes.mapIndexed() {index, node ->
//            val nodeInstructions = instructions.clone(index)
//            var currentId = node.id
//            var visitedCount = 1
//
//            while (currentId.last() != 'Z') {
//                visitedCount++
//                val instruction = nodeInstructions.step(nodes.size)
//                currentId = node.getNextId(instruction)
//            }
//        }
//
//        return BigInteger.valueOf(-1)
//        var count = BigInteger.ZERO
//        var i = 0
//        nodes.forEachIndexed { index, node -> println("${++i} ($index): ${node.id}") }
//
//        while (!allAtDestination(nodes)) {
//            count++
//            val nextInstruction = instructions.next
//            nodes = nodes.map { network.nodes[it.getNextId(nextInstruction)]!! }
//            nodes.forEachIndexed { index, node -> println("${++i} ($index): ${node.id}") }
//        }
//
//        return count
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
