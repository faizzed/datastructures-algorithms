package datastructures.stack

import java.util.Stack

fun main() {
    Stack<Int>().apply {
        push(1)
        push(2)
        push(4)
        pop().also(::println)
    }.also(::println)
}