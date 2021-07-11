package algorithms.search

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class JumpSearchTest {
    @Test
    fun `item exists in list` () {
        JumpSearch().search(2, listOf(3, 4, 5, 2).sorted()).also {
            assertTrue(it)
        }
    }

    @Test
    fun `item does not exists in list` () {
        JumpSearch().search(55, listOf(3, 4, 5, 2).sorted()).also {
            assertFalse(it)
        }
    }
}