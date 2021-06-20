package algorithms.sets

class LongestCommonSubsequence {
    fun lcs(X: CharArray, Y: CharArray, m: Int, n: Int): Int {
        if (m == 0 || n == 0) return 0

        return if (X[m - 1] == Y[n - 1]) {
            1 + lcs(X, Y, m - 1, n - 1)
        } else  {
            val lcsM = lcs(X, Y, m, n - 1)
            val lcsN = lcs(X, Y, m - 1, n)
            max(lcsM, lcsN)
        }
    }

    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }
}

fun main(args: Array<String>) {
    val lcs = LongestCommonSubsequence()
    val s1 = "EGG"
    val s2 = "AAGGE"
    val X = s1.toCharArray()
    val Y = s2.toCharArray()
    val m = X.size
    val n = Y.size
    println(
        "Length of LCS is" + " " +
                lcs.lcs(X, Y, m, n)
    )
}