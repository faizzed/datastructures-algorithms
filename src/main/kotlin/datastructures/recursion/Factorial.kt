package datastructures.recursion
/**
* The product of an integer and all its preceding positive integers
* */
fun factorial(n: Int): Int {
    if (n<=1) {
        return 1
    } else {
        return factorial(n-1) * n // The recursive call stack will have the followings
                                    // 1 * 2
                                    // 2 * 3
                                    // 6 * 4
                                    // 24 * 5
                                    // 120 * 6
    }
}

fun main() {
    println(factorial(3))
    println(factorial(4))
    println(factorial(6))
}