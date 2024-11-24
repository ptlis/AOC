package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import uk.org.fca.adventofcode.common.runLengthEncode

class RunLengthEncodeTest: FunSpec({
    test("Verify RLE algo") {
        runLengthEncode(listOf(true, true, false, true, false, false, false, false, true, true)) shouldBeEqual listOf(
            Pair(true, 2),
            Pair(false, 1),
            Pair(true, 1),
            Pair(false, 4),
            Pair(true, 2)
        )
    }
})