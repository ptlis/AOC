package uk.org.fca.adventofcode.y2023

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.y2023.day2.ColourCount
import uk.org.fca.adventofcode.y2023.day2.Draw
import uk.org.fca.adventofcode.y2023.day2.Game

class Day2Test: FunSpec({
    val colourLimits = mapOf(
        ColourCount.Colour.RED to 12,
        ColourCount.Colour.GREEN to 13,
        ColourCount.Colour.BLUE to 14
    )

    context("Part 1") {
        test("Parse colour count") {
            ColourCount.parse("7 blue") shouldBeEqual ColourCount(7, ColourCount.Colour.BLUE)
        }

        test("Parse draw") {
            val draw = Draw.parse("3 green, 2 blue, 5 red")
            draw shouldBeEqual Draw(listOf(
                ColourCount(3, ColourCount.Colour.GREEN),
                ColourCount(2, ColourCount.Colour.BLUE),
                ColourCount(5, ColourCount.Colour.RED)
            ))
        }

        test("Parse draws") {
            val draws = Draw.parseDraws("3 green, 2 blue, 5 red; 2 red, 3 blue; 1 green")
            draws shouldBeEqual listOf(
                Draw(listOf(
                    ColourCount(3, ColourCount.Colour.GREEN),
                    ColourCount(2, ColourCount.Colour.BLUE),
                    ColourCount(5, ColourCount.Colour.RED)
                )),
                Draw(listOf(
                    ColourCount(2, ColourCount.Colour.RED),
                    ColourCount(3, ColourCount.Colour.BLUE)
                )),
                Draw(listOf(ColourCount(1, ColourCount.Colour.GREEN)))
            )
        }

        test("Parse game") {
            val game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

            game shouldBeEqual Game(1, listOf(
                Draw(listOf(
                    ColourCount(3, ColourCount.Colour.BLUE),
                    ColourCount(4, ColourCount.Colour.RED)
                )),
                Draw(listOf(
                    ColourCount(1, ColourCount.Colour.RED),
                    ColourCount(2, ColourCount.Colour.GREEN),
                    ColourCount(6, ColourCount.Colour.BLUE)
                )),
                Draw(listOf(ColourCount(2, ColourCount.Colour.GREEN)))
            ))
        }

        context("test is draw possible") {
            withData(mapOf(
                "Draw 1" to Pair(
                    Draw(listOf(
                        ColourCount(3, ColourCount.Colour.GREEN),
                        ColourCount(2, ColourCount.Colour.BLUE),
                        ColourCount(5, ColourCount.Colour.RED)
                    )),
                    true
                ),
                "Draw 2" to Pair(
                    Draw(listOf(
                        ColourCount(20, ColourCount.Colour.GREEN),
                        ColourCount(5, ColourCount.Colour.BLUE),
                        ColourCount(9, ColourCount.Colour.RED)
                    )),
                    false
                )
            )) {
                data -> data.first.isPossible(colourLimits) shouldBeEqual data.second
            }
        }

        context("test is game possible") {
            withData(mapOf(
                "Game 11" to Pair(
                    Game(11, listOf(
                        Draw(listOf(
                            ColourCount(3, ColourCount.Colour.GREEN),
                            ColourCount(3, ColourCount.Colour.BLUE),
                            ColourCount(5, ColourCount.Colour.RED)
                        )),
                        Draw(listOf(
                            ColourCount(2, ColourCount.Colour.RED),
                            ColourCount(3, ColourCount.Colour.BLUE),
                        )),
                        Draw(listOf(ColourCount(1, ColourCount.Colour.GREEN)))
                    )),
                    true
                ),
                "Game 19" to Pair(
                    Game(19, listOf(
                        Draw(listOf(
                            ColourCount(3, ColourCount.Colour.GREEN),
                            ColourCount(30, ColourCount.Colour.BLUE),
                            ColourCount(5, ColourCount.Colour.RED)
                        )),
                        Draw(listOf(
                            ColourCount(2, ColourCount.Colour.RED),
                            ColourCount(99, ColourCount.Colour.BLUE),
                        )),
                        Draw(listOf(ColourCount(1, ColourCount.Colour.GREEN)))
                    )),
                    false
                )
            )) {
                data -> data.first.isPossible(colourLimits) shouldBeEqual data.second
            }
        }

        context("Test game is possible") {
            withData(mapOf(
                "Game 1" to Pair(Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"), true),
                "Game 2" to Pair(Game.parse("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"), true),
                "Game 3" to Pair(Game.parse("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"), false),
                "Game 4" to Pair(Game.parse( "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"), false),
                "Game 5" to Pair(Game.parse("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"), true)
            )) {
                data -> data.first.isPossible(colourLimits) shouldBeEqual data.second
            }
        }
    }

    context("Part 2") {
        context("Test get minimum colours count") {
            withData(mapOf(
                "Game 1" to Pair(
                    Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
                    mapOf(
                        ColourCount.Colour.RED to 4,
                        ColourCount.Colour.GREEN to 2,
                        ColourCount.Colour.BLUE to 6
                    )
                ),
                "Game 2" to Pair(
                    Game.parse("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
                    mapOf(
                        ColourCount.Colour.RED to 1,
                        ColourCount.Colour.GREEN to 3,
                        ColourCount.Colour.BLUE to 4
                    )
                ),
                "Game 3" to Pair(
                    Game.parse("Game 3: 3 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
                    mapOf(
                        ColourCount.Colour.RED to 20,
                        ColourCount.Colour.GREEN to 13,
                        ColourCount.Colour.BLUE to 6
                    )
                ),
                "Game 4" to Pair(
                    Game.parse("Game 4: 1 green, 3 red, 6 blue; 6 red, 3 green; 3 green, 15 blue, 14 red"),
                    mapOf(
                        ColourCount.Colour.RED to 14,
                        ColourCount.Colour.GREEN to 3,
                        ColourCount.Colour.BLUE to 15
                    )
                ),
                "Game 5" to Pair(
                    Game.parse("Game 5: 3 green, 6 red, 1 blue; 1 red, 2 green, 2 blue"),
                    mapOf(
                        ColourCount.Colour.RED to 6,
                        ColourCount.Colour.GREEN to 3,
                        ColourCount.Colour.BLUE to 2
                    )
                )
            )) {
                data -> data.first.minimumColoursCount shouldBeEqual data.second
            }
        }

        context("Test calculate power") {
            withData(mapOf(
                "Game 1" to Pair(
                    Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
                    48
                ),
                "Game 2" to Pair(
                    Game.parse("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
                    12
                ),
                "Game 3" to Pair(
                    Game.parse("Game 3: 3 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
                    1560
                ),
                "Game 4" to Pair(
                    Game.parse("Game 4: 1 green, 3 red, 6 blue; 6 red, 3 green; 3 green, 15 blue, 14 red"),
                    630
                ),
                "Game 5" to Pair(
                    Game.parse("Game 5: 3 green, 6 red, 1 blue; 1 red, 2 green, 2 blue"),
                    36
                )
            )) {
                    data -> data.first.power shouldBeEqual data.second
            }
        }
    }
})
