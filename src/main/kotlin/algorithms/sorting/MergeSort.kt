package algorithms.sorting

import misc.isset

class MergeSort {
    private fun merge(la: List<Int>, lb: List<Int>): MutableList<Int> {
        var laI = 0
        var lbI = 0
        val sortedList = mutableListOf<Int>()

        for (i in 0 until (la.size + lb.size)) {

            // check if one of the list has reached end
            if (!lb.isset(lbI)) {
                for (j in laI until la.size) {
                    sortedList.add(la[j])
                }
                break
            }

            if (!la.isset(laI)) {
                for (j in lbI until lb.size) {
                    sortedList.add(lb[j])
                }
                break
            }

            var item: Int
            if (la[laI] < lb[lbI]) {
                item = la[laI]
                laI++
            } else {
                item = lb[lbI]
                lbI++
            }
            sortedList.add(item)
        }

        return sortedList
    }

    fun sort(l: List<Int>): List<Int> {
        if (l.size <= 1) {
            return l
        }

        val m = l.size / 2
        return merge(sort(l.subList(0, m)), sort(l.subList(m, l.size)))
    }

}

fun main() {
    MergeSort().sort(listOf(5, 9, 12, 17, 19, 2, 8, 15, 18, 22, 23, 25)).also(::println)
    MergeSort().sort(listOf(5, 3, 6, 2, 1)).also(::println)
}