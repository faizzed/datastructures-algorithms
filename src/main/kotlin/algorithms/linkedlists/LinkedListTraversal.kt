package algorithms.linkedlists

class DoublyLinkedList {
    data class Node(val data: Int, var next: Node?, var prev: Node?)
    var head: Node? = null
    var tail: Node? = null

    fun add(data: Int) {
        if (head == null) {
            val temp = Node(data, null, null)
            head = temp
            tail = temp

        } else {
            tail?.next = Node(data, null, null)
            tail?.next?.prev = tail
            tail = tail?.next
        }
    }

    fun straightTraversal() {
        var temp = head
        while (true) {
            print("${temp?.data}\t")
            temp = temp?.next
            if (temp==null) {
                break
            }
        }
        print("\n")
    }

    fun reverseTraversal() {
        var temp = tail
        while (true) {
            print("${temp?.data}\t")
            temp = temp?.prev
            if (temp==null) {
                break
            }
        }
        print("\n")
    }
}

fun main() {
    DoublyLinkedList().apply {
        add(2)
        add(3)
        add(4)
        add(5)
        add(6)
        add(7)
        straightTraversal()
        reverseTraversal()
    }
}