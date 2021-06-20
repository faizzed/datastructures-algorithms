package algorithms.strings

import kotlin.math.min

/**
 * Given two strings such as cut and cat -
 * How many steps are required to convert cut into cat?
 * Steps can be adding, substituting (edit), or removal
 *
 * We start with making a length x length matrix and at each index assign the min value from add/remove/sub
 *  sub | remove
 *  ------------
 *  add| index
 *
 *  index in question considers the values at sub\remove\add that is j, i-1 | i, j-1 | i-1, j-1
 *  we find the min and +1 to it, if the str at indexes matches however, we take the value at sub position and add nothing to it
* */
class LevenshteinDistance {
    fun calculate(str1: String, str2: String): Int {
        val table = MutableList (str2.length+1) { oI ->
            MutableList(str1.length+1) {
                if (it == 0) oI
                else it
            }
        }

        for (i in 1..str2.length) {
            for (j in 1..str1.length) {
                var min = minimum(table[i][j-1], table[i-1][j], table[i-1][j-1])
                if (str2[i-1] != str1[j-1]) {
                    min++
                } else {
                    min = table[i-1][j-1]
                }

                table[i][j] = min
            }
        }
        return table[str2.length][str1.length]
    }

    fun minimum(a: Int, b: Int, c: Int): Int {
        return min(c, min(a, b))
    }
}

fun main() {
    LevenshteinDistance().calculate("biosm", "bisssadasdqed3eqwads").also(::println)
}