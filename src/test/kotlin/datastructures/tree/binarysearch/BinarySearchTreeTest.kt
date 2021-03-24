package datastructures.tree.binarysearch

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    private val binarySearchTree = BinarySearchTree()

    @BeforeEach
    fun init() {
        binarySearchTree.addAll(
            2, 5, 9, 14, 7, 11, 6, 18, 32, 4, 0, 88, 59
        )
    }

    @Test
    fun `test BST contain`() {
        binarySearchTree.contains(5).also {
            assertTrue(it)
        }

        binarySearchTree.contains(55).also {
            assertFalse(it)
        }
    }

    @Test
    fun `test BST min node`() {
        binarySearchTree.minNode().also {
            assertEquals(0, it?.data)
        }
    }

    @Test
    fun `test BST max node`() {
        binarySearchTree.maxNode().also {
            assertEquals(88, it?.data)
        }
    }

    @Test
    fun `test BST height`() {
        binarySearchTree.height().also {
            assertEquals(8, it)
        }
    }
}