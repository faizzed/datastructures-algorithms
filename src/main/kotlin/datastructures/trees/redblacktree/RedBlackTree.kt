package datastructures.trees.redblacktree

import datastructures.misc.NodeColor
import datastructures.misc.RbtNode
import datastructures.misc.TreePrinter
import java.util.*


/**
 * *POSTPONED*
 *  ---------
 *  Balancing trees via colors codes is just a bit too complicated.
 *
 * A red black tree as the name suggests has red and black nodes. It is a self-balancing binary tree.
 * Each node having two children and also a color code.
 *
 * The color code helps in balancing tree.
 * Unlike AVL tree which keep a height and balance factor against each node.
 *
 * On adding each node the following needs to be satisfied
 * - every node is either R or B
 * - there arent two adjacent parent child red nodes
 * - every path has exactly the same number of B nodes
 * - root is always B
 *
 * There are some instances that the tree may not be perfectly balanced as in AVL,
 * the balance factor maybe +2 or -2, which isn't that big of a deal but can happen.
* */
class RedBlackTree: Comparator<Int> {
    private var root: RbtNode<Int>? = null

    /**
     * Follow the same logic as BST and AVL, compare and insert to either the left
     * or right branches.
    * */
    fun insert(data: Int) {
        root = insert(root, data)
        root?.color = NodeColor.BLACK
    }

    fun insertAll(vararg data: Int) {
        data.forEach {
            root = insert(root, it)
            root?.color = NodeColor.BLACK
        }
    }

    private fun insert(node: RbtNode<Int>?, data: Int): RbtNode<Int> {
        if (node == null) {
            return RbtNode(data, null, null)
        }

        if (compare(node!!.data, data) < 0) {
            node.right = insert(node.right, data)
        } else {
            node.left = insert(node.left, data)
        }

        return balanceInsertion(node)
    }

    /**
     * Remove operation is the same as BST and AVL tree,
     * find the node, if its left or right sub-tree is null just return the next node on the path
     * If it has both sub-tress, find the min on the right sub-tree, replace it and delete the min node from right sub-tree
     * At the same time balance the nodes..
    * */
    fun remove(data: Int) {
        root = remove(root, data)
    }

    private fun remove(node: RbtNode<Int>?, data: Int): RbtNode<Int>? {
        var _node = node
        if (_node == null) {
            return null
        }

        if (compare(data, _node.data) < 0) {
            if (!isRed(_node.left) && !isRed(_node.left?.left)) {
                _node = moveRedLeft(_node!!)
            }

            _node?.left = remove(_node?.left, data)
        } else {
            if (isRed(_node.left)) {
                _node = rotateRight(_node)
            }

            if (compare(data, _node.data) === 0 && _node.right == null) {
                return null
            }

            if (!isRed(_node.right) && !isRed(_node.right!!.left)) {
                _node = moveRedRight(_node)
            }

            if (compare(data, _node!!.data) === 0) {
                val repl = findMin(_node?.right!!)
                _node.data = repl.data
                _node.right = deleteMin(_node.right!!)
            }
            else  {
                _node?.right = remove(_node?.right, data)
            }
        }

        return _node
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private fun moveRedLeft(node: RbtNode<Int>): RbtNode<Int> {
        var _node = node
        flipColors(_node)
        if (isRed(_node.right?.left)) {
            _node.right = rotateRight(_node.right)
            _node = rotateLeft(_node)
            flipColors(_node)
        }
        return _node
    }


    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private fun moveRedRight(node: RbtNode<Int>): RbtNode<Int>? {
        var _node = node
        flipColors(_node)
        if (isRed(_node.left?.left)) {
            _node = rotateRight(_node)
            flipColors(_node)
        }
        return _node
    }

    private fun findMin(node: RbtNode<Int>): RbtNode<Int> {
        var node = node
        while (node.left != null) {
            node = node.left!!
        }
        return node
    }


    // delete the key-value pair with the minimum key rooted at h
    private fun deleteMin(node: RbtNode<Int>): RbtNode<Int>? {
        var _node = node
        if (_node.left == null) {
            return null
        }
        if (!isRed(_node.left) && !isRed(_node.left?.left)) {
            _node = moveRedLeft(_node)
        }
        _node.left = deleteMin(_node.left!!)
        return balanceInsertion(_node)
    }

    /**
     * I have a few doubts over this balancing since this doesnt match with
     * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html%20//%205,%208,%203,%202,%201,%200,%2055,%2016,%204,%2013,%207,%206,%2066,%209,%2010,%2011,%2020,%2023,%2026,%2046,%2079
     * but this implementation satisfies the RBT variants and i is balanced at the end. That's all that is desired.
     *
     * For a node at any level 3 conditions are checked.
     * - Is the right node red? and the left isn't? if the left is black or null this satisfies -> rotate left
     * - Is the right node and the right right node both red. -> rotate right
     * - is both left and rigth node red -> flip colors
     *
     * On rotation, we rotate left or right and inherit color from the parent.
     * On flipping colors the parent and its two children just flip their colors.
     *
     * This is easy to get used to, it maybe a bit easier then AVL trees. But then again, AVL trees are much more
     * authentic.
    * */
    private fun balanceInsertion(node: RbtNode<Int>): RbtNode<Int> {
        var _node = node

        if (!isRed(_node.left) && isRed(_node.right)) {
            _node = rotateLeft(_node)
        }

        if (isRed(_node.left) && isRed(_node.left?.left)) {
            _node = rotateRight(_node)
        }

        if (isRed(_node.left) && isRed(_node.right)) {
            flipColors(_node)
        }
        return _node
    }

    private fun rotateRight(node: RbtNode<Int>?): RbtNode<Int> {
        val newParent = node?.left
        node?.left = newParent?.right
        newParent?.right = node
        newParent?.color = newParent?.right!!.color
        newParent?.right!!.color = NodeColor.RED
        return newParent
    }

    private fun rotateLeft(node: RbtNode<Int>?): RbtNode<Int> {
        val newParent = node?.right
        node?.right = newParent?.left
        newParent?.left = node
        newParent?.color = newParent?.left!!.color
        newParent.left!!.color = NodeColor.RED
        return newParent
    }

    private fun flipColors(node: RbtNode<Int>) {
        node.color = flip(node.color)
        node.left?.color = flip(node.left!!.color)
        node.right?.color = flip(node.right!!.color)
    }

    private fun flip(color: NodeColor): NodeColor {
        if (color == NodeColor.RED) {
            return NodeColor.BLACK
        }

        return NodeColor.RED
    }

    private fun isRed(node: RbtNode<Int>?): Boolean {
        return if (node == null) false else node.color === NodeColor.RED
    }


    override fun compare(o1: Int, o2: Int): Int {
        return o1-o2
    }

    override fun toString(): String {
        return """
            RBT < root=${root} >
        """.trimIndent()
    }

    fun printTree() {
        TreePrinter.print(root, 2)
    }
}

fun main() {
    RedBlackTree().apply {
        insertAll(5, 8, 3, 2, 1, 0, 55, 16, 4, 13)
        printTree()
        remove(16)
        remove(13)
        remove(2)
        printTree()
    }
}