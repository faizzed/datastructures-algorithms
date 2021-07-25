package datastructures.queue

import algorithms.graphs.Airports
import algorithms.graphs.Street
import java.util.*

fun main() {
    // priority queue isnt strictly FIFO or LIFO
    // it depends on the comparator and the priority set to each element
    // dont get bit in the ass by using it
    // use linked list instead
    val queue = PriorityQueue<Street>().apply {
        add(Street.Dusseltal)
        add(Street.Lennen)
        add(Street.Speldorf)
        add(Street.Augusten)
    }.also(::println)

    queue.poll()
    queue.poll()
    println(queue)

    val queue2 = PriorityQueue<Int>().apply {
        add(1)
        add(2)
        add(3)
        add(4)
    }.also(::println)

    queue2.poll()
    queue2.poll()
    println(queue2)

    val queue3 = LinkedList<Street>()
    queue3.add(Street.Dusseltal)
    queue3.add(Street.Lennen)
    queue3.add(Street.Speldorf)
    queue3.add(Street.Augusten)
    println(queue3)
    queue3.remove()
    queue3.remove()
    println(queue3)

}