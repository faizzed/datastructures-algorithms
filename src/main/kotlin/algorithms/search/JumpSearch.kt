package algorithms.search

import kotlin.math.sqrt

class JumpSearch {

    /**
    * Block size by recommendation is square root of size.
    */
    private fun blockSize(haystack: List<Int>): Int {
        return sqrt(haystack.size.toDouble()).toInt()
    }

    /**
     * Jump through blocks and check the high and low end of the block
     * if the element falls on edges on in between it search that block linearly
     * Or jump forwards...
     *
     * Jump forwards since the array is sorted.
     *
     * Time complexity:
     * O(n/m + m-1)
     * e.g array size (n) = 12, m=4 12/4 = 3 jumps
     * and then 3 linear searches
     * total: 3+3 = 6
     *
     * Better then linear search -> half in worst case.
     *
     * @param item item to be searched
     * @param haystack haystack has to be sorted.
    */
    fun search(item: Int, haystack: List<Int>): Boolean {
        val blockSize = blockSize(haystack)
        var start = 0
        var end = blockSize - 1

        for (i in 0..haystack.size / blockSize) {
            // compare element at start of block
            if (haystack[start] == item) {
                return true
            }
            // compare element at end of block
            else if (haystack[end] == item) {
                return true
            }
            // check if end element is lower then item
            // increment pointers
            else if (haystack[end] < item) {
                start = end + 1
                end += blockSize
                // if end is getting larger then size -- truncate end
                if (end > haystack.size-1) {
                    end = haystack.size-1
                }

                // if start is going out of bound return false --  since the arr is exhausted and nothing more to be checked
                if (start > haystack.size - 1) {
                    return false
                }
            }
            // check if end element is higher then item
            // start linear search
            else {
                for (j in start..end) {
                    if (haystack[j] == item) {
                        return true
                    }
                }
            }
        }

        return false
    }
}

fun main() {
    JumpSearch().search(99, listOf(23, 3, 18, 9, 6, 5, 4, 2, 6, 99, 55, 9, 18, 6).sorted()).also(::println)
    JumpSearch().search(1009, listOf(23, 3, 22, 9, 6, 5, 4, 2, 16, 99, 91, 92, 93, 94, 95, 96, 97, 98, 100, 55, 9, 18, 20).sorted()).also(::println)
    JumpSearch().search(55, listOf(3, 4, 5, 2).sorted()).also(::println)
}