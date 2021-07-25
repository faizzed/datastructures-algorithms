package datastructures.queue

import datastructures.misc.Node

class QueueForBst {
    private val queue = mutableListOf<Node<Int>>()

    fun add(node: Node<Int>) {
        queue.add(node)
    }

    fun poll(): Node<Int>? {
        if (!isEmpty()) {
            val item = queue[0]
            queue.removeAt(0)
            return item
        }
        return null
    }

    fun isEmpty(): Boolean {
        return queue.size == 0
    }
}