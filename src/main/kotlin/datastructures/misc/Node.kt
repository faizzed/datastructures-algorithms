package datastructures.misc

enum class NodeColor {
    RED, BLACK
}

/**
 * Every tree uses a node, a node is pretty standard with some data and left and right node.
 * This node should be used if printing the tree is desired.
 *
 * There isn't anything special about this class, just that its this one node that every tree
 * can/will use.
*/
data class Node<T>(
    var data: T,
    override var left: Node<T>?,
    override var right: Node<T>?,
    var height: Int = 0,
    var balanceFactor: Int = 0,
    var color: NodeColor = NodeColor.RED, // this is supposed to be a 1 bit information.. but hey!!
    var parent: Node<T>? = null
): PrintableNode<T> {

    override fun toString(): String {
        return """
            {data=$data, left=$left, right=$right, height=$height, balanceFactor=$balanceFactor}
        """.trimIndent()
    }

    override fun getText(): String {
        return data.toString()
    }
}

data class AvlNode<T>(
    var data: T,
    override var left: AvlNode<T>?,
    override var right: AvlNode<T>?,
    var height: Int = 0,
    var balanceFactor: Int = 0,
): PrintableNode<T> {

    override fun toString(): String {
        return """
            {data=$data, left=$left, right=$right, height=$height, balanceFactor=$balanceFactor}
        """.trimIndent()
    }

    override fun getText(): String {
        return data.toString()
    }
}

data class RbtNode<T>(
    var data: T,
    override var left: RbtNode<T>?,
    override var right: RbtNode<T>?,
    var color: NodeColor = NodeColor.RED, // this is supposed to be a 1 bit information.. but hey!!
    var parent: RbtNode<T>? = null
): PrintableNode<T> {

    override fun toString(): String {
        return """
            {data=$data, left=$left, right=$right, color=$color, parent=$parent}
        """.trimIndent()
    }

    override fun getText(): String {
        val d = data.toString()
        return if (color == NodeColor.RED) "${d}R" else "${d}B"
    }
}