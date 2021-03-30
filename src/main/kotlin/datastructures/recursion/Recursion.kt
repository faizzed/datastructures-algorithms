package datastructures.recursion

fun main() {
    println(
        recursivelyStoreIntoMap(2)
    )
}

private data class Args<T>(val input: T, val output: T)

/**
* We can collect in and out in recursive calls from the initial call to
 * the last call of the method.
* */
private fun recursivelyStoreIntoMap(a: Int): MutableList<Args<Int>> {
    var _a = a
    val l = mutableListOf<Args<Int>>()
    if (a < 8) {
        _a += 2
        l.add(Args(a, _a))
        l.addAll(recursivelyStoreIntoMap(_a))
    }

    return l
}