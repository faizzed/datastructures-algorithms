package datastructures.heap

import java.util.*

fun main() {
    /*
    * Priority queue is ordered as min queue.
    * Min element first..
    * */
    val pQueue = PriorityQueue<Int>().apply {
        add(11)
        add(32)
        add(99)
        add(2)
        add(4)
    }

    pQueue.also(::println)
    pQueue.poll().also(::println)
    pQueue.also(::println)
    pQueue.poll().also(::println)
    pQueue.also(::println)
    pQueue.poll().also(::println)
    pQueue.also(::println)
    pQueue.poll().also(::println)
    pQueue.also(::println)

    /*
    * A custom comparator can be used to define the order of
    * priority in the queue
    * */
    class HigherFirst : Comparator<Int> {
        override fun compare(a: Int, b: Int): Int {
            return if (a < b) 1 else -1
        }
    }

    val p2Queue = PriorityQueue(HigherFirst()).apply {
        add(2)
        add(3)
        add(99)
        add(44)
        add(6)
    }

    p2Queue.also(::println)
    p2Queue.poll().also(::println)
}