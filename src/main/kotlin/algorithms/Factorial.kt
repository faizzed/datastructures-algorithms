/**
 * Find factorial of a number
 *
 * Calculating n!
 * n=5
 * 5*4*3*2*1=n!
* */
package algorithms

/**
* Solve through recursion
* subtract and multiply until we reach 1
*/
private fun factorial(n: Int): Int {
    if (n>1) {
        return factorial(n-1) * n
    }

    return n
}

/**
* Solve through loop
*/
private fun factorial2(n: Long): Long {
    var _n = n
    var factorial = _n
    while (_n>1) {
        factorial *= (_n-1)
        _n -= 1
    }
    return factorial
}

fun main() {
    for (i in listOf(5, 3, 0, 1, 6, 9)) {
        println(factorial(i))
        println(factorial2(i.toLong()))
    }

    factorial2(13).also(::println)
}