package datastructures.queue

import datastructures.linkedlist.LinkedList

data class Person(val name: String)

/**
 * Queue:
 *
 * A queue is a list of elements with two functions enqueue aka [push, add] and dequeue aka [poll, remove, pull]
 * The elements are enqueued and dequeued in FIFO fashion
 * Imagine a line: A person that arrives first is served first.
 * */
class Queue {
    private val linkedList = LinkedList<Person>()

    fun push(person: Person) {
        linkedList.add(person)
    }

    fun poll() {
        linkedList.removeFirst()
    }

    override fun toString(): String {
        return linkedList.toString()
    }
}

fun main() {
    val queue = Queue()
    queue.push(Person("Peter"))
    queue.push(Person("Jack"))
    queue.push(Person("Chris"))

    queue.also(::println)

    queue.poll()
    queue.also(::println)
    queue.poll()
    queue.also(::println)
}