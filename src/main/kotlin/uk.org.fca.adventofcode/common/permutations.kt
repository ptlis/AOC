package uk.org.fca.adventofcode.common

/**
 * Yields all permutations of the provided list.
 *
 * Implements the iterative version of Heap's algorithm.
 */
fun <T> permutations( arr: List<T>) = iterator {
    val workingArray = arr.toMutableList()
    val c: MutableList<Int> = arr.indices.map { 0 }.toMutableList()

    yield(arr)

    var i = 1
    while (i < arr.size) {
        if (c[i] < i) {
            if (i % 2 == 0) {
                workingArray[0] = workingArray[i].also { workingArray[i] = workingArray[0] }
            } else {
                workingArray[c[i]] = workingArray[i].also { workingArray[i] = workingArray[c[i]] }
            }

            yield(workingArray.toList())

            c[i] += 1
            i = 1
        } else {
            c[i] = 0
            i += 1
        }
    }
}