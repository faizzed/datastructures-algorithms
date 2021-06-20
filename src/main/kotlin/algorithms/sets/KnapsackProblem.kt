package algorithms.sets

class KnapsackProblem {
    // A utility function that returns
    // maximum of two integers
    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    // Returns the maximum value that
    // can be put in a knapsack of
    // capacity W
    fun knapSack(capacity: Int, weights: IntArray, items: IntArray, n: Int): Int {
        // Base Case
        if (n == 0 || capacity == 0) {
            return 0
        }

        // If weight of the nth item is
        // more than Knapsack capacity W,
        // then this item cannot be included
        // in the optimal solution
        return if (weights[n - 1] > capacity) {
            knapSack(capacity, weights, items, n - 1)
        } else {
            val leftTreeWalk = knapSack(capacity - weights[n - 1], weights, items, n - 1)
            val rightTreeWalk = knapSack(capacity, weights, items, n - 1)
            max(items[n - 1] + leftTreeWalk, rightTreeWalk)
        }
    }
}

fun main(args: Array<String>) {
    val items = intArrayOf(60, 100, 120)
    val weights = intArrayOf(10, 20, 30)
    val capacity = 50
    val n = items.size
    println(KnapsackProblem().knapSack(capacity, weights, items, n))
}