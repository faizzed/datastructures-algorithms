package datastructures.queue

import java.util.*

fun main() {
    LinkedList<Int>().apply {
        push(1)
        push(2)
        push(3)
        push(4)
        poll().also(::println)
    }.also(::println)
}