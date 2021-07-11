package algorithms.search

/**
 * Find the middle of the arr
 * compare
 * == return true
 * > slice to right
 * < slice to left
 * repeat
*/
class BinarySearch {

    fun search(item: Int, haystack: List<Int>): Boolean {
        if (haystack.isEmpty()) {
            return false
        }

        val middleElm = haystack.size / 2

        return if (item == haystack[middleElm]) {
            true
        } else if (item > haystack[middleElm]) {
            search(item, haystack.slice(middleElm + 1 until haystack.size))
        } else {
            search(item, haystack.slice(0 until middleElm))
        }
    }
}

fun main() {
    BinarySearch().search(1, listOf(1, 2, 3, 4, 5, 6, 6).sorted()).also(::println)
    BinarySearch().search(8, listOf(1, 2, 3, 4, 5, 6, 7, 8).sorted()).also(::println)
    BinarySearch().search(8, listOf(1, 2, 3, 4, 5, 6, 7, 8).sorted()).also(::println)
    BinarySearch().search(55, listOf(3, 4, 5, 2).sorted()).also(::println)
}