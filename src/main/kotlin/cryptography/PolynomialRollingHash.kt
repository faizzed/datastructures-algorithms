package cryptography

fun polynomialRollingHash(str: String): Long {
    var hash: Long = 0
    val m = (1e9 + 9).toInt()
    var powerOfP: Long = 1
    val p = 53

    for (i in str.toCharArray()) {
        hash = ((hash + (i - 'a' + 1) * powerOfP) % m)
        powerOfP = (powerOfP * p) % m
    }
    return hash
}

fun main() {
    println(polynomialRollingHash("A brown box jumped over a lazy dog."))
    println(polynomialRollingHash("geeksforgeeks"))
}