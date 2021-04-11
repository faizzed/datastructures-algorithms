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

val logBase2: (Double) -> Double = { n -> ln(n) / ln(2.0) }