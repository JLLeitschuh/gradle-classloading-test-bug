package testing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinimalRequiredCodeTest {

    @Test
    fun simpleExampleCase() {
        val listOfElements = 0..100
        assertAll(listOfElements.map { _ ->
            {
                // This line is really important even though it does nothing.
                listOfElements
                assertEquals("wonderful", "wonderful") {
                    // Its very important that this lambda not use any externally scoped variables
                    "Should have worked"
                }
            }
        })
    }
}