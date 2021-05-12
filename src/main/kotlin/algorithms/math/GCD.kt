package algorithms.math

/**
 * GCD:
 *
 * Greatest common divisor as the name suggest is the greatest number that divides a set of numbers.
 * For more then two numbers, gcd can be found as gcd(gcd(a,b), c) and so on.. Two at a time.
 * If two numbers has a common divisor, it must be the same for the third, fourth numbers and so forth.. But i haven't verified this.
 *
 * Euclidean algorithm is used to calculate gcd. There are two variants of euclidean gcd
 * 1. start shrinking by subtracting a and b until both becomes equal. But this becomes ineffective if the numbers have
 *    greater difference.
 *
 * For example: a=18, b=5
 *
 *  (18-5), 5
 *  (13-5), 5
 *  (8-5), 5
 *  3, (5-3)
 *  (3-2), 2
 *  1, (2-1)
 *  1, 1
 *  so 1 is the gcd.
 *
 *  fun gcd(a: Int, b: Int):  Int {
 *      if (a==b) {
 *          return a
 *      }
 *
 *      return if (a>b) {
 *          gcd(a-b, b)
 *      } else {
 *          gcd(a, b-a)
 *     }
 *  }
 *
 * 2. Start shrinking by taking mod of either a or b until one of them becomes zero.
 * For example:
 * a=20, b=6
 * 6, (20%6)
 * 2, (6%2)
 * 2, 0
 * 2 is the gcd
* */
fun gcd(a: Int, b: Int):  Int {
    if (b==0) {
        return a
    }

    return gcd(b, a%b)
}

fun main() {
    gcd(gcd(gcd(15, 10), 30), 100).also(::println)
    gcd(20, 6).also(::println)
    gcd(48, 18).also(::println)
    gcd(10, 12).also(::println)
}