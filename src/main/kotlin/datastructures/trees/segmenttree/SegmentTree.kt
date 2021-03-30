package datastructures.trees.segmenttree

enum class SegmentTreeOps {
    MIN, MAX, SUM
}

/**
* Todo: The tree isn't debugged extensively for update operations on MIN and MAX
* */
class SegmentTree(private val arr: IntArray, private val treeOP: SegmentTreeOps = SegmentTreeOps.SUM) {
    private val segmentTree: IntArray

    /**
     * Create a segment tree array.
     * We will create a new array taking into account the size of our passed array and
     * calculate the possible resultant size for the array we will need to store our interval results
     * whatever they may be.
     *
     * How do we calculate the size of the final array?
     * ((log2 of (size of array) / log2 of (2)) ^ 2 - 1) * 2
     * Yeah its a mouth-full but in a sense we need to calculate the size of B-tree that will result
     * from adding the children together to make the complete tree.
    *
    * */
    init {
        val x = Math.ceil(Math.log(arr.size.toDouble()) / Math.log(2.0)).toInt()
        val max_size = 2 * Math.pow(2.0, x.toDouble()).toInt() - 1

        segmentTree = IntArray(max_size)
        initTree(arr, 0, arr.size - 1, 0)
    }

    /**
     * Constructing the segment tree:
     *
     * The cursor will start moving on the left most branch of the tree, until it reaches the edge node.
     * the from, to and mid variables help to get the index from the passed array to store at the edge nodes.
     * When the from and to var reaches to ==, it means the edge node has reached where-ever the cursor might be.
     *
     * the function recursively find edge nodes and on adding both left and right node values it adds the sum into the parent node.
     * recurse a step back and seed the next left/node and add again to the parent until it reaches back the root node
     * then it starts seeding the right-subtree.
     *
     * for example:
     * arr=[1,3,5,7,9,11]
     * The height of the tree is 4. I found it by drawing.
     * Left Sub Tree:
     * It happens that the left most nodes are at index 0, 1, 3, 7
     *                     left right nodes are at index      4, 8
     *
     * Right Sub Tree:
     * It happens that the right most nodes are at index 0, 2, 6, 14
     *                     left right nodes are at index      5, 13
     *
     * Taking a look at the cursor that's where it is trying to seed.
     * 2*cursor+1 finds the left parent
     * 2*cursor+2 finds the right parent.
     * And the from and to values try to find the indexes in the passed array that needs to be stored at those edges.
     *
     * Obviously the edges needs to be the values from the passed array.
     *
     * For the left tree. We split the array into half.
     * We start splitting the index into half because on each node at the tree the elements are split into half.
     *
     * Since the height is four, exactly at 4 operations
     * i.e
     * 0,5
     * 0,2
     * 0,1
     * 0,0
     * will we find the value form the left most edge and so on for others.
     * */
    private fun initTree(arr: IntArray, from: Int, to: Int, cursor: Int): Int {
        if (from == to) {
            segmentTree[cursor] = arr[from]
            return arr[from]
        }

        val mid = mid(from, to)
        val leftTree = initTree(arr, from, mid, cursor * 2 + 1)
        val rightTree = initTree(arr, mid + 1, to, cursor * 2 + 2)
        segmentTree[cursor] = getValue(leftTree, rightTree)
        return segmentTree[cursor]
    }

    private fun getValue(leftNode: Int, rightNode: Int): Int {
        return when(treeOP) {
            SegmentTreeOps.SUM -> leftNode + rightNode
            SegmentTreeOps.MIN -> minOf(leftNode, rightNode)
            SegmentTreeOps.MAX -> maxOf(leftNode, rightNode)
        }
    }

    /**
     * Find the value in range based on passed operation.
     * */
    fun range(queryFrom: Int, queryTo: Int): Int {
        // Check for erroneous input values
        if (queryFrom < 0 || queryTo > arr.size - 1 || queryFrom > queryTo) {
            println("Invalid Input")
            return -1
        }
        return range(0, arr.size - 1, queryFrom, queryTo, 0)
    }

    private fun mid(s: Int, e: Int): Int = s + (e - s) / 2

    /**
     * Sum follows the same pattern as seeding the tree.
     * Start with the left sub-tree, but this time check whether the window
     * of from-to falls inside || outside || partially against the range query window.
     *
     * If it falls inside the range query window -> return the value at cursor
     * If it doesn't return 0
     *
     * If those two arent the case, keep looking, meaning the windows partially overalp.
    * */
    private fun range(from: Int, to: Int, queryFrom: Int, queryTo: Int, cursor: Int): Int {
        if (from >= queryFrom && to <= queryTo) {
            return segmentTree[cursor]
        }

        // If segment of this node is outside the given range
        if (to < queryFrom || from > queryTo) {
            return when(treeOP) {
                SegmentTreeOps.MAX -> Integer.MIN_VALUE
                SegmentTreeOps.MIN -> Integer.MAX_VALUE
                SegmentTreeOps.SUM -> 0
            }
        }

        // If a part of this segment overlaps with the given range
        val mid = mid(from, to)
        val leftTree = range(from, mid, queryFrom, queryTo, 2 * cursor + 1)
        val rightTree = range(mid + 1, to, queryFrom, queryTo, 2 * cursor + 2)
        return getValue(leftTree, rightTree)
    }

    private fun update(from: Int, to: Int, replaceIndex: Int, diff: Int, cursor: Int) {
        if (replaceIndex < from || replaceIndex > to) {
            return
        }

        segmentTree[cursor] = getValue(segmentTree[cursor], diff)

        if (to != from) {
            val mid = mid(from, to)
            update(from, mid, replaceIndex, diff, 2 * cursor + 1)
            update(mid + 1, to, replaceIndex, diff, 2 * cursor + 2)
        }
    }

    /**
     * If the index falls out of range - do nothing
     * otherwise replace the value at the index and update the
     * segment tree with the difference.
    * */
    fun update(replaceIndex: Int, to: Int) {
        if (replaceIndex < 0 || replaceIndex > arr.size - 1) {
            println("Invalid Input")
            return
        }

        var diff = to - arr[replaceIndex]

        when(treeOP) {
            SegmentTreeOps.MIN -> diff = to
            SegmentTreeOps.MAX -> diff = to
        }

        arr[replaceIndex] = to

        update(0, arr.size - 1, replaceIndex, diff, 0)
    }

    override fun toString(): String {
        return segmentTree.toList().toString()
    }
}

fun main() {
    val arr = intArrayOf(-1, 3, 4, 0, 2, 1)

    SegmentTree(arr).apply {
        also(::println)
        range(1, 3).also(::println)
    }

    SegmentTree(arr, SegmentTreeOps.MIN).apply {
        also(::println)
        range(1, 3).also(::println)
    }

    SegmentTree(arr, SegmentTreeOps.MAX).apply {
        also(::println)
        range(1, 5).also(::println)
    }

    SegmentTree(arr, SegmentTreeOps.MIN).apply {
        also(::println)
        update(3, -6)
        also(::println)
        range(0, 5).also(::println)
    }

    SegmentTree(arr, SegmentTreeOps.MAX).apply {
        also(::println)
        update(3, 100)
        also(::println)
        range(0, 5).also(::println)
    }
}
