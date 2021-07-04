package algorithms.strings

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KmpTest {

    private val kmp = KMP()

    @Test
    fun testKmpPiTable() {
        val str = "abcdabeabf"
        val expectedPiTable = listOf(0, 0, 0, 0, 1, 2, 0, 1, 2, 0)

        kmp.kmpPiTable(str).also {
            assertEquals(it, expectedPiTable)
        }


        val str2 = "abababe"
        val expectedPiTable2 = listOf(0, 0, 1, 2, 3, 4, 0)

        kmp.kmpPiTable(str2).also {
            assertEquals(it, expectedPiTable2)
        }
    }

    @Test
    fun kmpTest() {
        kmp.knuthMorrisPrattAlgorithm("fox", "A brown fox jumped over a ....").also {
            assertEquals(it, 8)
        }

        kmp.knuthMorrisPrattAlgorithm("sheep", "A brown fox jumped over a ....").also {
            assertEquals(it, -1)
        }
    }
}