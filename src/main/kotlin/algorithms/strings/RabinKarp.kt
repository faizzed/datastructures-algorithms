package algorithms.strings

import algorithms.math.bitmanipulation.fastPower

class RabinKarp {

    fun hash(pattern: String): Int {
        var hash = 0
        for (i in pattern.indices) {
            hash += pattern[i].toInt() * 27.fastPower(pattern.length - i)
        }
        return hash
    }

    fun isSubstring(pattern: String, main: String): Boolean {
        for (i in 0..main.length - pattern.length) {
            val substr = main.substring(i, i+pattern.length)
                if (hash(pattern) == hash(substr)) {
                    return true
                }
        }
        return false
    }
}

fun main() {
    RabinKarp().isSubstring("aaed", "ccaccaaedba").also(::println)
}