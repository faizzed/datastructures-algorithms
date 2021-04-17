package misc

import java.util.*
import kotlin.math.ln

fun Int.toBinaryString(characters: Int = 8): String {
    return Integer.toBinaryString(this).padStart(characters, '0')
}

val randBetween: (Int, Int) -> Int = { a, b ->
    Random().nextInt(b - a) + a
}

val randBound: (Int) -> Int = { bound ->
    Random().nextInt(bound)
}

/**
 * How does log work?
 * Log is the inverse function of exponentiation.
 *
 * If 2^3=8 - then 2 is multiplied exponent times by itself, 2*2*2, that's exponentiation.
 * Inverse is, if need to know log of 8, it will be 3. Since log is base 2, it means 2 was multiplied 3 times to get 8
 * For log of 9, its 3.17, two was multiplied 3.17 times
 * or 2^3.17=9
* */
val logBase2: (Double) -> Double = {
    ln(it) / ln(2.0)
}