package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.permutations

class PermutationsTest: FunSpec({
    test("Verify expected permutations are generated") {
        val generatedPermutations = mutableListOf<List<Char>>()
        for (permutation in permutations(listOf('a', 'b', 'c'))) {
            generatedPermutations.add(permutation)
        }

        generatedPermutations shouldBeEqual mutableListOf(
            listOf('a', 'b', 'c'),
            listOf('b', 'a', 'c'),
            listOf('c', 'a', 'b'),
            listOf('a', 'c', 'b'),
            listOf('b', 'c', 'a'),
            listOf('c', 'b', 'a'),
        )
    }
})