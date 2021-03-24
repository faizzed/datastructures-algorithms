package datastructures.linkedlist

import java.util.LinkedList

fun main() {
    LinkedList<String>().apply {
        add("dd")
        add("dd")
        add("dd")
        add("dd")
        add("dd")
        remove()
        removeAt(0)
    }.also(::println)
}