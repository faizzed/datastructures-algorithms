package algorithms

import kotlin.math.abs

/**
 * LCM:
 *
 * Least common multiple as the name suggest is the smallest common multiple or n that is divisible by both a and b
 * for instance
 * a=10, b=12
 * 10=10, 20, 30, 40, 50, 60 ..
 * 12=12, 24, 36, 48, 60 ..
 *
 * There are other common multiple in this sequence but the LCM is 60
 *
 * We can use the following formula to find lcm
 *
 * lcm(a,b) = |a*b|/gcd(a,b)
 *
 * Explanation:
 * a=10, b=12
 * gcd(10,12)=2
 * 120/2=60
* */
val lcm: (Int, Int) -> Int = { a: Int, b: Int ->
    abs(a*b) / gcd(a, b)
}

fun main() {
    lcm(lcm(lcm(15, 10), 30), 100).also(::println)
    lcm(20, 6).also(::println)
    lcm(48, 18).also(::println)
    lcm(10, 12).also(::println)
}