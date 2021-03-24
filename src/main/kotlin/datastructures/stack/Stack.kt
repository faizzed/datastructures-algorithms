package datastructures.stack

import datastructures.linkedlist.LinkedList
/**
 * This is a very random type that doesnt explain very well the concept of stacks in real life.
 * But imagine if you turn a page and want to go back to the last visited page you would need
 * to go through the last page you visited.
 *
 * On another topic, Why do we even go as far as calling it a diff DT if the same operations can be
 * done via linked list and arrays?
* */
private data class Page(val number: Int)

/**
* Stack
 *
 * A stack is a list of elements that supports two operations
 * 1. push: pushes the element on top of the stack
 * 2. pop: take en element from the top of the stack.
 * 3. push and pop works in Last in first out (LIFO) fashion.
* */
private class Stack {
    private val linkedList = LinkedList<Page>()

    fun push(p: Page) {
        linkedList.add(p)
    }

    fun pop(): Page? {
        val page = linkedList.peek()
        linkedList.removeAt(linkedList.size-1)
        return page?.data
    }

    override fun toString(): String {
        return linkedList.toString()
    }
}

fun main() {
    val stack = Stack()
    stack.push(Page(1))
    stack.push(Page(2))
    stack.push(Page(3))
    stack.also(::println)

    stack.pop().also(::println)
}