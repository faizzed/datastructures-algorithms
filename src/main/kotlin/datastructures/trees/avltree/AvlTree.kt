package datastructures.trees.avltree

import datastructures.misc.AvlNode
import datastructures.misc.Node
import datastructures.misc.TreePrinter
import java.util.*

/**
 * This binary tree has the following properties that differentiate it from the rest and this called AVL.
 * (Maybe it was the first datastructure proposed - so others have to differentiate from it not the other way around maybe)
 *
 * - It is balanced - this is when left and right sub-trees are leveled and has only difference of 1 node, or none
 *   The balancing factor is described as BF(X) = {-1,0,1}, the balance factor of any node x is either -1, 1 or 0.
 *
 *   To visualize
 *
 *      2
 *     /\  -> perfectly balanced
 *    0  3
 *
 *
 *       2
 *      /\
 *     0  3 -> still good.
 *         \
 *          5
 *
 *
 *       2
 *      /\
 *     0  3
 *         \ -> this needs to be balanced.
 *          5
 *          \
 *           7
 *
 * - This begs the question, why do we need to balance? A long tail incur time complexity of O(n) rather then the desired
 *   O(log (n)).
 *
 * - If the balance factor is anything outside this set {1, -1, 0} we rotate the tree in 4 possible ways.
 *
* */
class AvlTree: Comparator<Int> {
    private var root: AvlNode<Int>? = null

    /**
     * Inserting elements into AVL tree is similar to BST.
     * We compare the element whether it should go to the left or right sub-tree
     * We find a null node and put it there.
    * */
    fun insert(data: Int) {
        root = insert(root, data)
    }

    fun insertAll(vararg data: Int) {
        data.forEach {
            root = insert(root, it)
        }
    }

    private fun insert(node: AvlNode<Int>?, data: Int): AvlNode<Int>? {
        if (node == null) {
            return AvlNode(data, null, null)
        }

        // data given is greater than root node
        // go to right..
        // this is BST property as well btw..
        if (compare(node.data, data) < 0) {
            node.right = insert(node.right, data)
        } else {
            node.left = insert(node.left, data)
        }

        // update node height and balance factor.
        update(node)
        return balance(node)
    }

    /**
     * removing from AVL tree has the same procedure as BST tree
     * find the node, if its at the edge remove it
     * if it has left or right sub-tree, find the min element in right sub-tree
     * replace it and remove the edge element.
     */
    fun remove(data: Int) {
        root = remove(root, data)
    }

    private fun remove(node: AvlNode<Int>?, data: Int): AvlNode<Int>? {
        if (node == null) {
            return null
        }

        if (compare(node.data, data) < 0) {
            node.right = remove(node.right, data)
        } else if(compare(node.data, data) > 0) {
            node.left = remove(node.left, data)
        } else {

            if (node.left == null) {
                return node.right
            } else if (node.right == null) {
                return node.left
            } else {
                val repl = findMin(node.right!!)
                node.data = repl.data
                node.right = remove(repl, repl.data)
            }
        }

        update(node)

        return balance(node)
    }

    /**
     * Every node in the tree will carry its height and balance factor
     * As we keep adding nodes, for each node we re-calculate the height of the entire branch we visited recursively.
     * The calculation of height and balance factor is as follows
     *
     *      2
     *     /\
     *    0  5
     *       /\
     *      3  6
     *
     * (2) recursion 0: height => -1 + 1 = 0, balance factor => -1 + (-1) = 0
     * (2) recursion 1 (inserting 0): height => 0 + 1 = 1, balance factor => -1 + (0) = -1
     * (2) recursion 2 (inserting 5): height => 0 + 1 = 1, balance factor => 0 + 0 = 0
     * (2) recursion 3 (inserting 3): height => 1 + 1 = 2, balance factor => 1 - 0 = 1
     * (2) recursion 4 (inserting 6): height => 1 + 1 = 2, balance factor => 1 - 0 = 1
    * */
    private fun update(node: AvlNode<Int>?) {
        val heightLeft = if (node?.left != null) node.left!!.height else -1
        val heightRight = if (node?.right != null) node.right!!.height else -1

        node?.height = Math.max(heightLeft, heightRight) + 1
        node?.balanceFactor = heightRight - heightLeft
    }

    /**
     * If the balance factor is not in the set {1, -1, 0}, it means the tree is heavy on either left or right side
     *
     * Balancing with the following rotations:
     * - Left left heavy -> rotate right
     * - Left right heavy -> rotate left, then right
     * - Right right heavy -> rotate left
     * - Right left heavy -> rotate right, then left
     *
     * Some indicators:
     * -n represents a left heavy tree since right-left (3-5) = -2 means left is heavy.
     * +n represents that right is heavy, since right-left or (5-3) = +2 represents that..
    * */
    private fun balance(node: AvlNode<Int>): AvlNode<Int>? {
        // tree is left heavy
        if (node.balanceFactor == -2) {
            // left left heavy
            if (node.left?.balanceFactor!! <= 0) {
                return leftLeftRotation(node)
            }
            // left right heavy
            else {
                return leftRightRotation(node)
            }

        }else if(node.balanceFactor == +2) {
            // right right heavy
            if (node.right?.balanceFactor!! >= 0) {
                return rightRightRotation(node)
            }
            // right left heavy
            else {
                return rightLeftRotation(node)
            }
        }
        // balanced.. no need.
        return node
    }

    /**
    * About rotations:
     * There are only two rotations - left and right rotation.
     * There are four cases - either left or right is heavy, inside that condition either the left or right leaf is heavy.
     *
     * There is a very simple rule to it.
     * In left rotation, since its left heavy, rotate right..
     * How to rotate?
     * 1. create a new node from right/left of parent.
     * 2. Assign right/left node of created node to the right/left node of parent.
     * 3. Assign parent to left/right of new node.
     *
     * left/right depends on where the tree is tilting.
    * */
    private fun leftLeftRotation(node: AvlNode<Int>): AvlNode<Int>? {
        return rightRotation(node)
    }

    private fun rightRightRotation(node: AvlNode<Int>): AvlNode<Int>? {
        return leftRotation(node)
    }

    private fun leftRightRotation(node: AvlNode<Int>): AvlNode<Int>? {
        node.left = leftRotation(node.left!!)
        return leftLeftRotation(node)
    }

    private fun rightLeftRotation(node: AvlNode<Int>): AvlNode<Int>? {
        node.right = rightRotation(node.right!!)
        return rightRightRotation(node)
    }

    private fun rightRotation(node: AvlNode<Int>): AvlNode<Int>? {
        val newParent = node.left
        node.left = newParent?.right
        newParent?.right = node
        update(node)
        update(newParent)
        return newParent
    }

    private fun leftRotation(node: AvlNode<Int>): AvlNode<Int>? {
        val newParent = node.right
        node.right = newParent?.left
        newParent?.left = node
        update(node)
        update(newParent)
        return newParent
    }

    private fun findMin(node: AvlNode<Int>): AvlNode<Int> {
        var min = node
        while (min.left != null) {
            min = min.left!!
        }

        return min
    }

    fun search(data: Int): AvlNode<Int>? {
        val res = search(root, data)
        if (res != null) {
            return AvlNode(res.data, null, null)
        }

        return res
    }

    /**
     * Search follows the same principle as BST, nothing changes..
     * Compare and start looking in either sub-tree until its found
     * or return null
    * */
    private fun search(node: AvlNode<Int>?, data: Int): AvlNode<Int>? {
        if (node == null) {
            return null
        }

        if (node.data == data) {
            return node
        }

        if (compare(node.data, data) < 0) {
            return search(node.right, data)
        } else {
            return search(node.left, data)
        }
    }

    override fun compare(el1: Int, el2: Int): Int {
        return el1 - el2
    }

    override fun toString(): String {
        return """
            AVL< root=$root >
        """.trimIndent()
    }

    fun printTree() {
        TreePrinter.print(root, 2)
        println("\n\n")
    }
}

fun main() {
    // left left heavy tree
    AvlTree().apply {
        insertAll(77, 6, 88, 5, 4, 3)
        also(::println)
        printTree()
    }

    // left right heavy tree
    AvlTree().apply {
        insertAll(77, 6, 88, 5, 62, 64, 65, 60, 63)
        also(::println)
        printTree()
    }

    // right right heavy tree
    AvlTree().apply {
        insertAll(77, 76, 78, 79, 80)
        also(::println)
        printTree()
    }

    // right left heavy tree
    AvlTree().apply {
        insertAll(1, 0, 22, 19, 23, 18, 20)
        also(::println)
        printTree()
        remove(22)
        remove(23)
        remove(20)
        printTree()
    }

    // search tree..
    AvlTree().apply {
        insertAll(1, 0, 22, 19, 23, 18, 20, 3, 33, 445, 87, 9, 363, 2, 5, 6, 3,2, 55, 66, 32, 23, 545)
        also(::println)
        printTree()
        search(363).also(::println)
        search(1000).also(::println)
    }
}