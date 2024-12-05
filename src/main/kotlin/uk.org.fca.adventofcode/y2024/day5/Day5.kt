package uk.org.fca.adventofcode.y2024.day5

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day5: Day() {
    override fun part1Solution(): BigInteger =
        BigInteger.valueOf(solvePart1(dayData).toLong())

    override fun part2Solution(): BigInteger =
        BigInteger.valueOf(solvePart2(dayData).toLong())

    override val day: Int get() = 5
    override val year: Int get() = 2024

    companion object {
        fun solvePart1(rawData: List<String>): Int {
            val rawPageOrderingRules = rawData.filter { it.length == 5 }
            val rawPagesToProduce = rawData.filter { it.length > 5 }

            val pageOrderingRules = rawPageOrderingRules.map { it.split('|').map { num -> num.toInt() } }
            val pagesToProduce = rawPagesToProduce.map { it.split(',').map { num -> num.toInt() } }

            val pageOrderingRulesByFirst = pageOrderingRules.groupBy({ it[0] }) { it[1] }
            val pageOrderingRulesBySecond = pageOrderingRules.groupBy({ it[1] }) { it[0] }

            val validPagesToProduce = pagesToProduce.filter {
                isPagesToProduceValid(it, pageOrderingRulesByFirst, pageOrderingRulesBySecond)
            }

            return validPagesToProduce.sumOf { it[it.size / 2] }
        }

        fun solvePart2(rawData: List<String>): Int {
            val rawPageOrderingRules = rawData.filter { it.length == 5 }
            val rawPagesToProduce = rawData.filter { it.length > 5 }

            val pageOrderingRules = rawPageOrderingRules.map { it.split('|').map { num -> num.toInt() } }
            val pagesToProduce = rawPagesToProduce.map { it.split(',').map { num -> num.toInt() } }

            val pageOrderingRulesByFirst = pageOrderingRules.groupBy({ it[0] }) { it[1] }
            val pageOrderingRulesBySecond = pageOrderingRules.groupBy({ it[1] }) { it[0] }

            val sortedPageOrder = pageOrderingRulesByFirst.toList()
                .sortedBy { (_, value) -> value.size }
                .reversed()
                .map { it.first }
                .toMutableList()
            val remaining = pageOrderingRulesBySecond.keys.filter { !pageOrderingRulesByFirst.containsKey(it) }
            if (remaining.isNotEmpty()) {
                sortedPageOrder.add(remaining.last())
            }

            val inValidPagesToProduce = pagesToProduce.filter {
                !isPagesToProduceValid(it, pageOrderingRulesByFirst, pageOrderingRulesBySecond)
            }

            val reOrderedPagesToProduce = inValidPagesToProduce.map { invalidPages ->
                sortedPageOrder.filter { invalidPages.contains(it) }
            }

            return reOrderedPagesToProduce.sumOf { it[it.size / 2] }
        }

        fun isPagesToProduceValid(
            pagesToProduce: List<Int>,
            pageOrderingRulesByFirst: Map<Int, List<Int>>,
            pageOrderingRulesBySecond: Map<Int, List<Int>>,
        ): Boolean {
            return pagesToProduce.filterIndexed() { index, page ->
                val precedingPages = pagesToProduce.slice(0..<index)
                val succeedingPages = pagesToProduce.slice(index+1..<pagesToProduce.size)
                succeedingPages.all { pageOrderingRulesByFirst.contains(page) && pageOrderingRulesByFirst[page]?.contains(it)!! } &&
                        precedingPages.all { pageOrderingRulesBySecond.contains(page) && pageOrderingRulesBySecond[page]?.contains(it)!! }
            }.size == pagesToProduce.size
        }
    }
}
