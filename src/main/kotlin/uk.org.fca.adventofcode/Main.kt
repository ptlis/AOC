package uk.org.fca.adventofcode

import uk.org.fca.adventofcode.`2023`.Day1
import uk.org.fca.adventofcode.`2023`.Day2
import uk.org.fca.adventofcode.`2023`.Day3
import uk.org.fca.adventofcode.`2023`.Day4

fun main () {
    val day1Data = object {}.javaClass.getResourceAsStream("/2023/day1")?.bufferedReader()?.readLines() ?: listOf()
    val day1 = Day1()

    println("Day 1 Part 1: ${day1.part1Solution(day1Data)}")
    println("Day 1 Part 2: ${day1.part2Solution(day1Data)}")

    val day2Data = object {}.javaClass.getResourceAsStream("/2023/day2")?.bufferedReader()?.readLines() ?: listOf()
    val day2 = Day2()

    println("Day 2 Part 1: ${day2.part1Solution(day2Data)}")
    println("Day 2 Part 2: ${day2.part2Solution(day2Data)}")


    val day3Data = object {}.javaClass.getResourceAsStream("/2023/day3")?.bufferedReader()?.readLines() ?: listOf()
    val day3 = Day3()

    println("Day 3 Part 1: ${day3.part1Solution(day3Data)}")
    println("Day 3 Part 2: ${day3.part2Solution(day3Data)}")


    val day4Data = object {}.javaClass.getResourceAsStream("/2023/day4")?.bufferedReader()?.readLines() ?: listOf()
    val day4 = Day4()

    println("Day 4 Part 1: ${day4.part1Solution(day4Data)}")
    println("Day 4 Part 2: ${day4.part2Solution(day4Data)}")
}