package algorithms.search

/**
 * Interpolation search uses a formula to find an elament in uniformly distributed array
 * If the array is UD, the time complexity is O(1)
 * otherwise it might increase depending on how uniformly distanced the elements are.
 *
 * Better then BS when data is UD, otherwise might be worse.
*/
class InterpolationSearch {

    val pos: (Int, List<Int>) -> Int = { item, haystack ->
        val l = haystack.indices.first
        val h = haystack.indices.last
        val up = item  - haystack[l]
        val down = haystack[h] - haystack[l]
        val sub = h-l
        l + ( up/down) * sub
    }

    fun search(item: Int, haystack: List<Int>): Boolean {

        if (haystack.isEmpty()) {
            return false
        }

        val pos = pos(item, haystack)

        if (pos > haystack.size-1) {
            return false
        }

        return if (item == haystack[pos]) {
            true
        } else if (item > haystack[pos]) {
            search(item, haystack.slice(pos + 1 until haystack.size))
        } else {
            search(item, haystack.slice(0 until pos))
        }
    }
}

fun main() {
    InterpolationSearch().search(1, listOf(1, 2, 3, 4, 5).sorted()).also(::println)
    InterpolationSearch().search(5, listOf(1, 2, 3, 4, 5).sorted()).also(::println)
    InterpolationSearch().search(11, listOf(1, 55, 99, 66, 11).sorted()).also(::println)
    InterpolationSearch().search(30, listOf(1, 7, 13, 17, 19, 23, 45, 54, 28, 26, 30, 35).sorted()).also(::println)
}