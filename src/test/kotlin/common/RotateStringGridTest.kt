package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.rotateStringGrid

class RotateStringGridTest: FunSpec({
    test("Rotate grid") {
        val grid = """
            1234
            5678
            90ab
            cdef
        """.trimIndent().lines()

        rotateStringGrid(grid) shouldBeEqual """
            159c
            260d
            37ae
            48bf
        """.trimIndent().lines()
    }
})
