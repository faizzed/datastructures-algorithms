package algorithms.trees

import datastructures.trees.binarysearchtree.BinarySearchTree

fun main() {
    BinarySearchTree().apply {
        addAll(8, 16, 3, 2, 11, 9, 18)
        // depth first search - visit either subtree and then start with the next..
        preOrderTraversal()
        println()
        // Breadth first search - visit each nodes of the child and then move to the next level
        levelOrderTraversal()
        println()
        printGraph()
    }
}