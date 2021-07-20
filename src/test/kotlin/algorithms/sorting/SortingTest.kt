package algorithms.sorting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SortingTest {
    @Test
    fun `test bubble sort` () {
        Sorting().bubble(listOf(2, 1, 3)).also {
            assertEquals(it, listOf(1, 2, 3), "Lists does not match!!")
        }

        Sorting().bubble(listOf(3,45,123,856,346,788,43,234,64,3,1)).also {
            assertEquals(it, listOf(1, 3, 3, 43, 45, 64, 123, 234, 346, 788, 856), "Lists does not match!!")
        }
    }

    @Test
    fun `test selection sort` () {
        Sorting().selectionSort(listOf(2, 1, 3)).also {
            assertEquals(it, listOf(1, 2, 3), "Lists does not match!!")
        }

        Sorting().selectionSort(listOf(3,45,123,856,346,788,43,234,64,3,1)).also {
            assertEquals(it, listOf(1, 3, 3, 43, 45, 64, 123, 234, 346, 788, 856), "Lists does not match!!")
        }
    }
}