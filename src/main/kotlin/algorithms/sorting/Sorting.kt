package algorithms.sorting

import datastructures.heap.Heap
import misc.findMinByIndex
import java.util.*

class Sorting {
    /**
     * start comparing two elements from the beginning and sort them asc or desc
     * Do asc
     * Time complexity: O(n2)
     * Isnt used in standard libraries - just educational tool
    */
    fun bubble(l: List<Int>): List<Int> {
        val _l = l.toMutableList()
        var swapped: Boolean

        for (x in 0 until _l.size) {
            swapped = false
            for (i in 0.._l.size-2) {
                if (_l[i] > _l[i+1]) {
                    val swap = _l[i]
                    _l[i] = _l[i+1]
                    _l[i+1] = swap
                    swapped = true
                }
            }

            if (!swapped) {
                break
            }
        }

        return _l.toList()
    }

    /**
     * Same as bubble sort in complexity
     * Start at the beginning, find the min element and put it at the first index, inc swap index
     * start another loop from the current index and check for another min and so on..
    */
    fun selectionSort(list: List<Int>): List<Int> {
        val sortedList = mutableListOf<Int>()
        val _list = list.toMutableList()
        for (i in _list.indices) {
            val minIndex = _list.findMinByIndex()
            sortedList.add(_list[minIndex])
            _list.removeAt(minIndex)
        }
        return sortedList
    }

    /**
     * Sorting starts at the left most index, every next element is compared with its left most elements until the location
     * its less/greater then the left element reached. Then the index is swapped.
    */
    fun insertionSort(list: List<Int>): List<Int> {
        val _l = list.toMutableList()
        for (i in _l.indices) {
            for (j in i downTo 1) {
                if (_l[j] < _l[j-1]) {
                    val swap = _l[j]
                    _l[j] = _l[j-1]
                    _l[j-1] = swap
                }
            }
        }

        return _l
    }

    /**
     * Binary trees are either max trees or min trees.
     * Meaning parent is either max then children or less then children
     * Our custom implementation has max tree so we can sort it from there...
    */
    fun heapSort(list: List<Int>): MutableList<Int> {
        val sortedL = mutableListOf<Int>()
        val maxHeap = Heap(list.size).also { h ->
            list.forEach {
                h.insert(it)
            }
        }

        list.forEach { _ ->
            sortedL.add(0, maxHeap.remove()!!)
        }

        return sortedL
    }

    /**
     * Binary trees are either max trees or min trees.
     * Meaning parent is either max then children or less then children
     * Std priority queue is min tree so we are good...
    */
    fun heapSortStdImp(list: List<Int>): MutableList<Int> {
        val sortedL = mutableListOf<Int>()
        val minHeap = PriorityQueue<Int>(list.size).also { h ->
            list.forEach {
                h.add(it)
            }
        }

        list.forEach { _ ->
            sortedL.add(minHeap.remove())
        }

        return sortedL
    }
}

fun main() {
    Sorting().heapSortStdImp(listOf(3, 4, 1, 2, 6)).also(::println)
    Sorting().heapSortStdImp(listOf(12, 1, 6, 3, 2)).also(::println)
    Sorting().heapSortStdImp(listOf(3,45,123,856,346,788,43,234,64,3,1)).also(::println)
}