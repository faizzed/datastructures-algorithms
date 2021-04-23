package algorithms

import misc.factorial

/**
 * Pascal triangle
 * Pascal triangle is a triangular array of binomial coefficients which has alot of applications in mathematics.
 * There are certain application of binomial coefficients in combinatorics and statistics.
 * Those will be covered in other problems.
 *
 *
 * To make a pascal triangle we will use the formula of binomial coefficients
 * where an element at row c and index k is represented as
 * n(c, k) = n!/k!(n-k)!
*/
class PascalTriangle {
    /**
    * Here height means how many rows there will be in the triangle
    * */
    fun ofHeight(height: Long) {
        for (i in 0..height) {
            for (j in 0..i) {
                val n = binomialCoefficientOfN(i, j)
                print("$n\t")
            }
            print("\n")
        }
    }

    /**
     * @param n = row
     * @param k = index
    * */
    private fun binomialCoefficientOfN(n: Long, k: Long): Long {
        val denominator = k.factorial() * (n-k).factorial()

        return if (denominator > 0) {
            n.factorial() / denominator
        } else {
            1
        }

    }
}

fun main() {
    PascalTriangle().apply {
        ofHeight(15)
    }
}