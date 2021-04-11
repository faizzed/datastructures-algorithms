/**
 * Fibonacci sequence
 * A sequence where every n is the sum of its two predecessors, starting with 0,1
 * 0,1,1,2,3,5,8,13....
* */
package algorithms

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
private fun fibonacciNumberAtIndexN(n: Int): Int {
    if (n<=1) {
        return n
    }

    val n1 = fibonacciNumberAtIndexN(n-1)
    val n2 = fibonacciNumberAtIndexN(n-2)

    return n1 + n2
}

fun main() {
    for (i in listOf(6, 18, 3, 0, 1, 2, 5)) {
        fibonacciNumberAtIndexN(i).also(::println)
    }
}
