package uk.org.fca.adventofcode.y2023

import uk.org.fca.adventofcode.Day
import uk.org.fca.adventofcode.y2023.day7.Card
import uk.org.fca.adventofcode.y2023.day7.Game
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
