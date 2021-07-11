package algorithms.strings

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RabinKarpTest {

    @Test
    fun `test that string matches`() {
        RabinKarp().isSubstring("abc", "ababababaababc").also {
            assertTrue(it)
        }
    }

    @Test
    fun `test that string does not matches`() {
        RabinKarp().isSubstring("abd", "ababababaababc").also {
            assertFalse(it)
        }
    }
}
