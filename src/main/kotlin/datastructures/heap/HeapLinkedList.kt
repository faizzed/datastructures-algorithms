package datastructures.heap

import datastructures.linkedlist.LinkedList

/**
* Heap:
 *
 * Heaps are implemented via arrays representing nodes of a binary tree
 * A binary tree constructing a heap satisfies heap properties, such as min-heap or max-heap
 * In a min heap: A parent is less then either of its children.
 * In a max heap: A parent is greater then either of its children.
 *
 * In a given array [0,1,2,3,4,5]
 * i=0 element is considered root and i=1 is considered left node, i=2 is considered right node
 * The same goes on considering each child at i=1, i=2 as parents and repeating the process subsequently
 *
 * To satisfy the heap properties, while inserting the elements, they are compared with the parent
 * if i=1 > i=0 || i=2 > i=0 then swap i=1 with i=0 or vice versa
 *
 * While traversing the array to find parent or children of a given element at i=n
 * Children are located at:
 * Left child = 2(i)+1
 * Right child = 2(i)+2
 *
 * To determine the parent of a child
 * Parent = 2(i-1)/2
 *
 * Using these formulas we can sift up (max heap) or sift down (min heap) our elements to make a heap.
* */
class HeapLinkedList {

    private var linkedList = LinkedList<Int>()
    /**
    * Used to identify the parent for sifting-up or sifting-down
    * */
    private fun parent(i: Int): Int = (i - 1) / 2
    fun leftChild(i: Int): Int = (2*i)+1
    fun rightChild(i: Int): Int = (2*i)+2

    fun insert(element: Int) {
        linkedList.add(element)
        siftUp(linkedList.size - 1)
    }

    fun remove() {
        if (linkedList.size == 0) {
            return
        }

        removeAndSwapTopElement()
        siftDown(0)
    }

    /**
     * After adding an element to the end of the arr
     * Start comparing it with parent, if the parent is less then its child
     * replace the parent index with its child index and continue comparing until
     * the parent is greater then its children
    * */
    private fun siftUp(i: Int) {
        if (linkedList.size > 1 && linkedList.getAt(parent(i))!!.data < linkedList.getAt(i)!!.data) {
            linkedList.swap(parent(i), i)
            siftUp(parent(i))
        }
    }

    /**
     * start comparing top element to its children, replace with the max child
     * continue with the max child index until there is no children left
    * */
    private fun siftDown(i: Int) {
        val leftChild = leftChild(i)
        val rightChild = rightChild(i)
        var maxChild = i

        if (leftChild < linkedList.size && linkedList.getAt(leftChild)!!.data > linkedList.getAt(i)!!.data) {
            maxChild = leftChild
        }

        if (rightChild < linkedList.size && linkedList.getAt(rightChild)!!.data > linkedList.getAt(maxChild)!!.data) {
            maxChild = rightChild
        }

        if (maxChild != i) {
            linkedList.swap(i, maxChild)
            siftDown(maxChild)
        }
    }

    private fun removeAndSwapTopElement(): Int? {
        linkedList.swap(0, linkedList.size-1)
        val removeEl = linkedList.removeAt(linkedList.size-1)
        return removeEl?.data
    }

    override fun toString(): String {
        return linkedList.toString()
    }
}

fun main() {
    val heap = HeapLinkedList()

    arrayOf(9, 8, 11, 6, 4, 99, 4, 55, 2, 4, 5, 6, 77, 8, 11, 10).forEach {
        heap.insert(it)
    }

    heap.also(::println)

    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()
    heap.remove()

    heap.also(::println)
}