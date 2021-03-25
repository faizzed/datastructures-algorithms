package datastructures.misc

/**
 * Every tree uses a node, a node is pretty standard with some data and left and right node.
 * This node should be used if printing the tree is desired.
 *
 * There isn't anything special about this class, just that its this one node that every tree
 * can/will use.
*/
data class Node<T>(var data: T, override var left: Node<T>?, override var right: Node<T>?): PrintableNode<T> {
    override fun toString(): String {
        return """
            {data=$data, left=$left, right=$right}
        """.trimIndent()
    }

    override fun getText(): String {
        return data.toString()
    }
}