package datastructures.queue

import datastructures.linkedlist.LinkedList
import datastructures.linkedlist.Node

data class Person(val name: String)

/**
 * Queue:
 *
 * A queue is a list of elements with two functions enqueue aka [push, add] and dequeue aka [poll, remove, pull]
 * The elements are enqueued and dequeued in FIFO fashion
 * Imagine a line: A person that arrives first is served first.
 * */
class Queue<T> {
    private val linkedList = LinkedList<T>()

    fun push(person: T) {
        linkedList.add(person)
    }

    fun poll(): Node<T>? {
        return linkedList.removeFirst()
    }

    fun isEmpty(): Boolean {
        return linkedList.size == 0
    }

    override fun toString(): String {
        return linkedList.toString()
    }
}

fun main() {
    val queue = Queue<Person>()
    queue.push(Person("Peter"))
    queue.push(Person("Jack"))
    queue.push(Person("Chris"))
    queue.poll().also(::println)
    queue.also(::println)
}