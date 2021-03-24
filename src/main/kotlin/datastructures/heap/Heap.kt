package datastructures.heap

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
class Heap() {
    private var arr = arrayOfNulls<Int>(10)
    var size = 0
    constructor(size: Int): this() {
        arr = arrayOfNulls(size)
    }

    /**
    * Used to identify the parent for sifting-up or sifting-down
    * */
    private fun parent(i: Int): Int = (i - 1) / 2
    fun leftChild(i: Int): Int = (2*i)+1
    fun rightChild(i: Int): Int = (2*i)+2

    /**
    * Swap elements in the array for achieving min or max heap
    * */
    private fun swap(index1: Int, index2: Int) {
        val temp = arr[index1]
        arr[index1] = arr[index2]
        arr[index2] = temp
    }

    fun insert(element: Int) {
        arr[size] = element
        size++
        siftUp(size - 1)
    }

    fun remove() {
        if (size == 0) {
            throw Exception("Tree is empty")
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
        if (size > 1 && arr[parent(i)]!! < arr[i]!!) {
            swap(parent(i), i)
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

        if (leftChild < size && arr[leftChild]!! > arr[i]!!) {
            maxChild = leftChild
        }

        if (rightChild < size && arr[rightChild]!! > arr[maxChild]!!) {
            maxChild = rightChild
        }

        if (maxChild != i) {
            swap(i, maxChild)
            siftDown(maxChild)
        }
    }

    private fun removeAndSwapTopElement(): Int? {
        val removeEl = arr[0]
        arr[0] = arr[size-1]
        arr[size-1] = null
        size--
        return removeEl
    }

    override fun toString(): String {
        return arr.toList().toString()
    }
}

fun main() {
    val heap = Heap(20)

    arrayOf(9, 8, 11, 6, 4, 3, 55, 3, 9, 0, 6, 8, 99).forEach {
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