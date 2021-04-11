/**
 * Determine whether a number is power of two?
 * */
package algorithms.bitmanipulation

import misc.logBase2
import misc.randBound

/**
 * We can solve this problem 3 ways
 * 1. Using log base 2 of n
 * 2. Dividing the number until remainder is one.
 * 3. Using bit manipulation
* */
class PowerOfTwo {
    /**
     * Find log base 2 of the number
     * if its modulo is 0 then its a power of 2 otherwise not
     * Skip n=1 and n=2 -- tricky numbers
    * */
    fun usingLogN(n: Int): Boolean {
        val logOfN = logBase2(n.toDouble())

        // if n is 2, log of 2 is 1
        if (logOfN == 1.0) {
            return true
        }

        // otherwise check the modulo of 2 if its zero
        return logOfN % 2 == 0.0
    }

    /**
     * In my opinion this will take more time
     * Keep dividing the number by 2 until the remainder is 1
     * in between check modulo, if modulo is not 0 and the n is not 1 then its not power of 2
    * */
    fun usingDivision(n: Int): Boolean {
        if (n==0) return false
        var r = n.toDouble()
        while (r != 1.0) {
            r /= 2
            if (r%2 != 0.0 && r != 1.0) {
                return false
            }
        }
        return true
    }

    /**
     * Most interesting and efficient is using bitwise operations
     * If n is power of 2 then it will have exactly 1 bit set the rest will be unset
     *
     * In order to find which number has only one bit set, we can & it will its preceding number will result in 0
     * such as
     * 16 = 10000
     * 15 = 01111
     * 16 & 15 = 0
     *
     * This doesnt apply to zero
     * although it satisfies
     * 0 = 0000
     * -1 = 1111
     * 0&-1=0
     * but still 0 isn't the power of 2
    * */
    fun usingBitTwiddling(n: Int): Boolean {
        if (n==0) return false
        return n and (n-1) == 0
    }
}

fun main() {
    PowerOfTwo().apply {
        for (i in 1..10) {
            val n = randBound(30)
            print("$n ${if(usingLogN(n)) "=" else "!=" } power of 2\n")
            print("$n ${if(usingDivision(n)) "=" else "!=" } power of 2\n")
            print("$n ${if(usingBitTwiddling(n)) "=" else "!=" } power of 2\n")
            println("\n")
        }
    }
}