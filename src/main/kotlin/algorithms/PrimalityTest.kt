/**
 * A number is prime its only divisible by 1 and itself.
* */
package algorithms

import kotlin.math.sqrt

class PrimalityTest {
    fun isPrime(n: Int): Boolean {
        // prime numbers start with 2
        if (n<2) {
            return false
        }

        if (n==2) {
            return true
        }

        // check if its divisible by 2. If it is then its not prime
        if (n%2==0) {
            return false
        }

        // since we know that prime numbers are not even
        // only 2 is prime which is even
        // we will check within odd numbers
        // and since we are looking for division, we will skip numbers > n/2 since they
        // cant divide the number anyway..
        for (i in 3..(sqrt(n.toDouble()).toInt()) step 2) {
            if (n%i==0) {
                return false
            }
        }

        return true
    }
}

fun main() {
    PrimalityTest().apply {
        for(i in 1..1000) {
            isPrime(i).also {
                if (it) {
                    print("$i\t")
                }
            }
        }
    }
}