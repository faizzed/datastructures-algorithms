/**
* Is the given number even?
* */
package algorithms.bitmanipulation

import misc.randBound

/**
 * Two ways
 * 1. If modulo by 2 == 0
 * 2. If last bit is 0
 *
 * !IsEven would be considered Odd
* */
class IsEven {
    fun moduloBy2(n: Int): Boolean {
        return n%2==0
    }

    /**
     * How do we know if the last bit is 0?
     * 1 has last bit set and the rest 0
     * suppose 16
     * 16: 1000
     * 1:  0001 -> 16 & 1 -> 0 -> yes
     *
     * suppose 17
     * 17: 10001
     * 1:  00001 -> 17 & 1 -> 1 -> no
    * */
    fun bitTwiddling(n: Int): Boolean {
        return n and 1 == 0
    }
}

fun main() {
    IsEven().apply {
        for (i in 1..10) {
            val n = randBound(100)
            print("$n is ${if(moduloBy2(n)) "even" else "odd"}\n")
            print("$n is ${if(bitTwiddling(n)) "even" else "odd"}\n")
        }
    }
}