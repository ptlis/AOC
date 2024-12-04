package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.extractDiagonals

class ExtractDiagonalsTest: FunSpec({
    test("Rotate grid") {
        val grid = """
            1234
            5678
            90ab
            cdef
        """.trimIndent().lines()

        extractDiagonals(grid) shouldBeEqual listOf(
            "1",
            "25",
            "369",
            "470c",
            "8ad",
            "be",
            "f"
        )
    }
})
