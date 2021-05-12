/**
 * Fibonacci sequence
 * A sequence where every n is the sum of its two predecessors, starting with 0,1
 * 0,1,1,2,3,5,8,13....
* */
package algorithms.math

import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt

class FibonacciSequence {
    /**
     * Find a fibonacci number at position n
     * The recursion works as follows -
     * We keep subtracting until n reaches 1 or less then 1 in that case we return n
     * Call stack 6: n1=n-1=5=5, n2: n-2=4=3 = (5+3) = 8
     *            5: n1=n-1=4=3, n2: n-2=3=2 = (3+2) = 5
     *            4: n1=n-1=3=2, n2: n-2=2=1 = (2+1) = 3
     *            3: n1=n-1=2=1, n2: n-2=1=1 = (1+1) = 2
     *            2: n1 = 1, n2: 2-2 = 0 = (1+0) = 1
     *            1: return 1
     * */
    fun numberAtIndexUsingRecursion(index: Int): Int {
        if (index <= 1) {
            return index
        }

        val n1 = numberAtIndexUsingRecursion(index - 1)
        val n2 = numberAtIndexUsingRecursion(index - 2)

        return n1 + n2
    }

    fun numberAtIndexUsingLoops(index: Int): Int {
        if (index <= 1) {
            return index
        }

        var preOne = 0
        var preTwo = 1
        var nAtIndex = 0

        for (i in 1 until index) {
            nAtIndex = preOne + preTwo
            preOne = preTwo
            preTwo = nAtIndex
        }

        return nAtIndex
    }

    // n = 1/sqrt(5) ((1+sqrt(5)/2)sqr - (1-sqrt(5)/2)sqr)
    fun binetFormula(index: Int): Int {
        val squareRootOf5 = sqrt(5F)

        val gn: (n: Int) -> Double = {
            ((1 + sqrt(5.toDouble()))/2).pow(it)
        }

        val en: (n: Int) -> Double = {
            ((1 - sqrt(5.toDouble()))/2).pow(it)
        }

        return ceil((gn(index) - en(index)) / squareRootOf5).toInt()
    }
}

fun main() {
    FibonacciSequence().apply {
        for (i in listOf(6, 18, 3, 0, 1, 2, 5)) {
            numberAtIndexUsingRecursion(i).also(::println)
        }

        println("Loops:")

        for (i in listOf(6, 18, 3, 0, 1, 2, 5)) {
            numberAtIndexUsingLoops(i).also(::println)
        }

        println("Binet Formula:")

        for (i in listOf(6, 18, 3, 0, 1, 2, 5)) {
            binetFormula(i).also(::println)
        }
    }
}
