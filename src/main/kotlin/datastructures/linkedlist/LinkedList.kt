package datastructures.linkedlist

/**
* Why do we need this Node?
 * A node represent data and its link to the next node.
* */
data class Node<T>(var data: T, var next: Node<T>?) {
    fun toString2(): String {
        return "data=${data}"
    }
}

/**
* Linked List:
 *
 * A linked list is a chain of elements connected via pointers pointing to the next node
 * A null pointer represent the end of the chain..
*
* */
class LinkedList<T> {
    // At the beginning of the list, there is nothing
    // The start and end of the list are both empty
    var head: Node<T>? = null
    var tail: Node<T>? = null
    var size = 0

    /**
     * Time complexity: O(1)
     * */
    fun add(key: T) {
        // check whether the list has elements, if not add it to head and tail
        if (head == null) {
            /*
            * Why temp?
            * We assign the same address to both head and tail
            * tail will move the pointer in memory on each add() operation, while head will keep inventory of elements
            * */
            val temp = Node<T>(key, null)
            head = temp
            tail = temp
        } else {
                /*
                * Whats going on here?
                * We populate the next null pointer on tail
                * This will reflect in head too
                * And set tail on the last element
                * */
                tail?.next = Node(key, null)
                tail = tail?.next
        }
        size++
    }

    /**
    * Add at the beginning of the list
    * */
    fun addFirst(element: T) {
        val temp = Node(element, head)
        head = temp
        size++
    }

    /**
    * Remove element from the beginning to list
    * */
    fun removeFirst(): Node<T>? {
        val el = head?.copy()
        el?.next = null
        val temp = head?.next
        head = temp
        size--
        return el
    }

    /**
    * Add an element at specific index
     * How do we do that?
     * Loop through the nodes -
     * Attach the element at the next pointer
    * */
    fun addAt(element: T, index: Int): Boolean {

        if (index == 0) {
            addFirst(element)
            return true
        }

        var temp = head
        for (i in 0 until index-1) {
            temp = temp?.next
        }

        if (temp == null) {
            return false
        } else {
            val node = Node(element, temp.next)
            // its the end node, update tail.
            if (temp.next == null) {
                tail = node
            }
            temp.next = node
        }

        size++

        return true
    }

    /**
    * Remove an element from the list
     * How do we do that?
     * Find the node at index - attach the next element to the last element pointer
    * */
    fun removeAt(index: Int): Node<T>? {

        if (index == 0) {
            return removeFirst()
        }

        var temp = head
        val el: Node<T>?
        for (i in 0 until index-1) {
            temp = temp?.next
        }

        if (temp == null) {
            return temp
        } else {
            el = temp.next
            temp.next = temp.next?.next
        }

        size--

        return el
    }

    /**
    * Get the last node without removing it.
    * */
    fun peek(): Node<T>? {
        var temp = head
        while (temp?.next != null) {
            temp = temp.next
        }

        return temp
    }

    /**
     * Time complexity: O(n)
     * */
    fun get(element: T): Node<T>? {
        var temp = head
        while(temp != null) {
            if (temp.data?.equals(element) == true) {
                return Node(temp.data, null)
            }
            temp = temp.next
        }
        return null
    }

    /**
     * Replace: O(n)
     * */
    fun replace(element: T, with: T): Node<T>? {
        var temp = head
        while(temp != null) {
            if (temp.data?.equals(element) == true) {
                temp.data = with
                return null
            }
            temp = temp.next
        }
        return null
    }

    /**
     * Time complexity: O(n)
     * */
    fun getAt(index: Int): Node<T>? {
        var temp = head
        for (i in 0 until index) {
            temp = temp?.next
        }

        if (temp == null) {
            return null
        }

        return Node(temp.data, null)
    }

    /**
     * Time complexity: O(n)
     * */
    fun swap(indexOne: Int, indexTwo: Int) {
        var itemOne = head
        for (i in 0 until indexOne) {
            itemOne = itemOne?.next
        }

        var itemTwo = head
        for (i in 0 until indexTwo) {
            itemTwo = itemTwo?.next
        }

        if (itemOne == null || itemTwo == null) {
            return
        }

        val tempValue = itemOne.data
        itemOne.data = itemTwo.data
        itemTwo.data = tempValue
    }

    /**
     * Time complexity: O(n)
     * */
    fun exists(element: T): Boolean {
        return get(element) != null
    }

    override fun toString(): String {
        var output = "{"
        var temp = head
        while (temp != null) {
            val itemMid = temp.toString2() + ", "
            val itemLast = temp.toString2()
            temp = temp.next
            if (temp == null) {
                output += itemLast
            } else {
                output += itemMid
            }
        }
        output += "}"
        return output
    }
}

fun main() {
    val linkedList = LinkedList<String>().apply {
        add("sparrow")
        add("crow")
        add("lion")
        add("pigeon")
        addFirst("tiger")
    }

    linkedList.addAt("mouse", 1)
    linkedList.addAt("hen", 6)
    linkedList.addAt("chicken", 0)

    linkedList.size.also(::println)
    linkedList.also(::println)

    linkedList.removeAt(0)
    linkedList.removeAt(0)
    linkedList.size.also(::println)
    linkedList.also(::println)

    linkedList.add("Hello")
    linkedList.add("World")
    linkedList.add("Mars")
    linkedList.size.also(::println)
    linkedList.also(::println)

    linkedList.removeAt(2)
    linkedList.size.also(::println)

    linkedList.also(::println)
    linkedList.get("lion").also(::println)
    linkedList.getAt(2).also(::println)
    linkedList.also(::println)
    linkedList.swap(0, 3)
    linkedList.swap(1, 6)
    linkedList.swap(1, 13)
    linkedList.also(::println)
    linkedList.also(::println)
    linkedList.replace("Hello", "Hola")
    linkedList.also(::println)
}