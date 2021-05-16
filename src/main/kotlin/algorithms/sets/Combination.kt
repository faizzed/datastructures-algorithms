package algorithms.sets
/**
 * Combination:
 * Number of ways to arrange elements in a set where order does not matter
 * Formula
 * n!/r!(n-r)!
 * s={a,b,c}
 * 3!/2!(3-2)!, where r=2, we want subsets of 2 elements from this set
 * total combinations = 3
* */
class Combination {
    fun produce(arr: Array<String>, len: Int, startPosition: Int, result: Array<String?>) {
        if (len == 0) {
            println(result.toList())
            return
        }

        for (i in startPosition..arr.size - len) {
            result[result.size - len] = arr[i]
            produce(arr, len - 1, i + 1, result)
        }
    }
}

fun main() {
    val arr = arrayOf("A", "B", "C")
    Combination().produce(arr, 2, 0, arrayOfNulls(2))
}