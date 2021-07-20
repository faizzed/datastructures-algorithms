package algorithms.sorting

class QuickSort {
    /**
     * The idea is to find the right sort index for the first element in this list.
     * Check elements from 1st - size-1 index
     * Swap element if element on left end is greater and element on right end is less then the pivot element.
     * Keep swapping until the cursors overlap.
    */
    fun sort(list: MutableList<Int>, l: Int, h: Int): MutableList<Int> {
        val partition = fun(l: Int, h: Int): Int {
            val swap = fun(i: Int, j: Int) {
                val temp = list[i]
                list[i] = list[j]
                list[j] = temp
            }

            val pivot = list[h]
            var pIndex = l
            for (i in l until h) {
                if (list[i] < pivot) {
                    swap(pIndex, i)
                    pIndex++
                }
            }
            swap(pIndex, h)
            return pIndex
        }

        if (l<h) {
            val partitionIndex = partition(l, h)
            sort(list, l, partitionIndex-1)
            sort(list, partitionIndex+1, h)
        }

        return list
    }
}

fun main() {
    val list = listOf(10, 16, 8, 12, 15, 6, 3, 9, 5).toMutableList()
    QuickSort().sort(list, 0, list.size - 1).also(::println)
}