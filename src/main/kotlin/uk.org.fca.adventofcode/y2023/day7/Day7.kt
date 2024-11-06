package uk.org.fca.adventofcode.y2023.day7

import uk.org.fca.adventofcode.Day
import java.math.BigInteger

class Day7: Day() {
    override fun part1Solution(): BigInteger {
        return Game.parse(dayData, Card.Type.JACK).winnings
    }

    override fun part2Solution(): BigInteger {
//        return Game.parse(dayData, Card.Type.JOKER).winnings
        return BigInteger.valueOf(-1)
    }

    override val number get() = 7
}
