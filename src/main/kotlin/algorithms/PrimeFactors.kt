/**
* Prime factors of a number are the smallest prime numbers multiplied together to get the number.
* */
package algorithms

import kotlin.math.sqrt

class PrimeFactors {

    fun ofN(n: Int): MutableList<Int> {
        val l = mutableListOf<Int>()
        var _n = n
        while (_n%2 == 0) {
            l.add(2)
            _n/=2
        }

        for (i in 3..sqrt(_n.toDouble()).toInt() step 2) {
            while (_n%i==0) {
                l.add(i)
                _n/=i
            }
        }

        if (_n>2) {
            l.add(_n)
        }
        return l
    }
}

fun main() {
    PrimeFactors().apply {
        ofN(228).also(::println)
        ofN(12).also(::println)
        ofN(147).also(::println)
    }
}