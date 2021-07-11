package algorithms.search

/**
 * Search every item in the array linearly..
 * Time Complexity: O(n)
*/
class LinearSearch {
    fun <T> search(item: T, haystack: List<T>): Boolean {
        for (i in haystack) {
            if (item == i) {
                return true
            }
        }

        return false
    }
}

fun main() {
    LinearSearch().search("hey", "Hello world hey man".split(" ")).also(::println)
    LinearSearch().search(1, listOf(1,2,3,4,4,5)).also(::println)
}