package datastructures.tree.binarysearch

import java.util.*

data class Node(var data: Int, var left: Node?, var right: Node?) {
    override fun toString(): String {
        return """
            {data=$data, left=$left, right=$right}
        """.trimIndent()
    }
}

/**
 * A binary search tree is a sorted search tree with properties as follows
 *  - left node has value less than root node
 *  - right node has value greater than root node
 *
 *  This makes the left sub-tree of BST smaller than the right sub-tree
 *
 *  Adding element, retrieval and removing takes O(log n) time meaning doubling the size of the tree only increase operations by times 1.
 *  This makes BST better than unsorted arrays.
* */
class BinarySearchTree: Comparator<Int> {
    private var root: Node? = null

    /**
     * Recursively find the left or right sub-tree node where the data must be added
     * On getting a new value, it traverses the left or right subtree until a null node is found.
     *
     * e.g 2, 3, 4, 1, 5
     *
     *          2
     *         / \
     *        1   3
     *             \
     *              4
     * if we wanted to add 5, this method will start with the root node (2) and check whether the
     * left or right sub-tree should be traversed. Since the value 5 is greater than 2 it will start with the right
     * node 3, passing node 3 to this method, it will again check which sub-tree to traverse.
     * Again 5 is greater than 3, it will take 4 and make the same decision again, comparing 5 and 4
     * it again is the right sub-tree, but there is no nodes at the right sub-tree of 4, therefore it encounter null and
     * add 5 at the right leaf and unwind to the initial call issued by root node 2.
     *
     * A recursive method keep track of all calls and their returns
     * Imagine if node (2) has called recursively node 3, 4 and than 5 than it knows which value was passed
     * as input, in this case node 3, node 4 and node 5 and what was the output of those calls.
     * The output was a node itself and its assignment to either right leaf or left leaf.
     *
     * otherwise it checks the value if its greater than the node value in question which is supposed to be
     * the root of the new value, than it adds the data to the right leaf, if its less or equal to the root
     * value than it adds it to the left leaf.
     * */
    fun add(data: Int) {
        root = add(root, data)
    }

    /**
    * add varargs
    * */
    fun addAll(vararg data: Int) {
        data.forEach {
            root = add(root, it)
        }
    }

    private fun add(node: Node?, data: Int): Node {
        var _node = node
        if (_node == null) {
            _node = Node(data, null, null)
        } else {
            if (compare(_node.data, data) < 0) {
                _node.right = add(_node.right, data)
            } else {
                // insert less or equal node on left side.
                _node.left = add(_node.left, data)
            }
        }

        return _node
    }

    /**
     * Check if tree contains data?
     * Start at the root node, and traverse the tree left or right depending on compare operation result
     * */
    fun contains(data: Int): Boolean {
        return contains(root, data) != null
    }

    fun find(data: Int): Node? {
        return contains(root, data)
    }

    private fun contains(node: Node?, data: Int): Node? {

        var response: Node? = null

        if (node == null) {
            return response
        }

        if (data == node.data) {
            return Node(data, null, null)
        }

        if (compare(node.data, data) < 0) {
            if (node.right != null) {
                contains(node.right, data)?.let {
                    response = it
                }
            }
        } else {
            if (node.left != null) {
                contains(node.left, data)?.let {
                    response = it
                }
            }
        }

        return response
    }

    /**
     * How do we remove from BST?
     *
     * We traverse the tree left of right based on comparing the data until we reach the node where its data is equal to
     * the data we want to remove.
     *
     * What do we do than?
     * 1. If the left sub-tree is null, assign the right node in place of this parent
     *
     *      3
     *     /\
     *    2  5
     *        \
     *         8
     *  On removing 5, 8 will be assigned to the right node of 3.
     *  This is handled by recursion right after the compare < 0 line.
     *
     * 2. If the right sub-tree is null the same will happen, just invert the tree to imagine.
     *
     * 3. If the node has both left and right sub-tree, we can parse the min element in right sub-tree or the max
     * element in left-tree.
     *
     * In our example,
     *
     *       10
     *       /\
     *     5   11
     *    /\
     *   4  7
     *     /\
     *    6  8
     *
     * assume 5 needs to be removed, traverse its right sub-tree starting from node 7. We would find that
     * 6 is the min element in this sub-tree, because min elements are always on the left sub-tree. Thats the property
     * of BST.
     *
     * Somehow we need to replace this min element with the element to be removed - 5
     * we swap values, in that sense the min element has replaced 5.
     * We still need to remove 6, for that we pass our right sub-tree starting from 7 to the remove function
     * it finds 6 and since it has neither right or left sub-tress a null is returned and assigned to the
     * left node of 7.
    * */
    fun remove(data: Int) {
        root = remove(root, data)
    }

    private fun remove(node: Node?, data: Int): Node? {
        // tree is empty
        if (node == null) {
            return null
        }

        // go to right
        if (compare(node.data, data) < 0) {
            node.right = remove(node.right, data)
        } else if(compare(node.data, data) > 0) {
            node.left = remove(node.left, data)
        } else {

            // This is the node we wish to remove..

            // if left sub-tree is null, replace the node with its child
            if (node.left == null) {
                return node.right
            }
            // same as before..
            else if (node.right == null) {
                return node.left
            }
            // if it has both sub-trees find min on the right side and replace it
            else {
                val repl = minOnDemand(node.right!!)
                node.data = repl.data
                node.right = remove(node.right, repl.data)
            }
        }

        return node
    }

    fun minNode(): Node? {
        var node = root
        while (node?.left != null) {
            node = node.left
        }
        return node
    }

    private fun minOnDemand(_node: Node): Node {
        var node = _node
        while (node.left != null) {
            node = node.left!!
        }
        return node
    }

    fun maxNode(): Node? {
        var node = root
        while (node?.right != null) {
            node = node.right
        }
        return node
    }

    fun height() {
        
    }

    override fun compare(el1: Int, el2: Int): Int {
        return el1 - el2
    }

    override fun toString(): String {
        return """
            BST(root=$root)
        """.trimIndent()
    }
}

fun main() {
    BinarySearchTree().apply {
        addAll(2, 5, 9, 14, 7, 11, 6, 18, 32, 4, 0, 88, 59)
        also(::println)
        contains(5).also(::println)
        contains(99).also(::println)
        find(5).also(::println)
        find(32).also(::println)
        minNode().also(::println)
        maxNode().also(::println)
        remove(11)
        also(::println)
        remove(9)
        also(::println)
    }
}