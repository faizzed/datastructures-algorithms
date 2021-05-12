package algorithms.math.bitmanipulation

import java.time.Instant

/**
 * Finding power of n
 * Power of n^p where n=base p=power, would traditionally be like so
 * 2^4 = 2*2*2*2
 * This results in O(n) complexity.
 *
 * If we were to reduce the amount of computations, upto O(log(n))
 * We can use divide and conquer algorithm
 * 2^4 = (2^2) * (2^2)
 * But we wouldn't compute the second part or the 3rd.
 * 2^4 would be (2^2) = 4 * 4 = 16
 *
 * For even numbers this is calculated as n^p/2 * n^p/2
 * For odd numbers this is calculated as n^p/2 * n^p/2 * n
 *
* */
fun Int.isEven(): Boolean = this % 2 == 0

fun Int.fastPower(p: Int): Int {
    if (p==0) return 1

    if (p.isEven()) {
        /**
         * call stack
         * 2^4
         * p=4=even=>p2*p2=>16
         * p=2=even=>p1*p1=>4
         * p=1=odd=>p0*p0*2=>2
         * p=0=odd=>1=>1
         *
         * */
        val mul = fastPower(p/2)
        return mul * mul
    } else {
        /**
         * call stack
         * 2^3
         * p=3=odd=2*2*2
         * p=1=odd=1*1*2
         * p=0=odd=1
        * */
        val mul = fastPower(p/2)
        return mul * mul * this
    }
}


fun main() {
    for (i in 0..10) {
        (2).fastPower(i).also {
            print("2^$i=$it\n")
        }
        (3).fastPower(i).also {
            print("3^$i=$it\n")
        }
    }

    println(Instant.now().epochSecond)
}
