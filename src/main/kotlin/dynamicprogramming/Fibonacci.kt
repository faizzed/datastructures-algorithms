package dynamicprogramming

import java.util.concurrent.atomic.AtomicInteger

// 1 1 2 3 5 8
class Fibonacci {
    var counter = AtomicInteger(0)
    val memo = mutableMapOf<Int, Int>()
    /**
     * Recursive stack for this method
     *      3 => 2
     *     /\
     *    2  1->1
     *   /\
     1<-1  0
     * We branch out into two stacks and the reaching to <= 1 we return either 0 or 1 and we add it from branches.
     * From our node we can see how many nodes we traverse before we conclude the fibonacci number at index n
     *
    */
    fun fibonacciSeriesRecursive(index: Int): Int {
        print("${counter.incrementAndGet()} ")
        if (index <= 1) {
            return index
        }

        return fibonacciSeriesRecursive(index-1) + fibonacciSeriesRecursive(index-2)
    }

    /**
     * For index 25 this node traversal increases to 242785
     * this quickly becomes a time complexity concern.
     *
     * On drawing the fibonacci recursive tree its apparent that many of the branching can be
     * avoided since we already know the answer for those nodes from either left or right sub-branch
     *
     * We will store the nodes we traverse and avoid traversing them if we already know their answer.
     * This will reduce the node traversing hence the time complexity.
    */
    fun fibonacciSeriesRecursiveMemoization(index: Int): Int {
        print("${counter.incrementAndGet()} ")
        memo[index]?.let {
            return it
        }

        if (index <= 1) {
            return index
        }

        memo[index] = fibonacciSeriesRecursiveMemoization(index-1) + fibonacciSeriesRecursiveMemoization(index-2)
        return memo[index]!!
    }
}

/*
* Nodes traversal reduced by 2692537-59=2692478 with DP approach.
* */
fun main() {
    val startTime1 = System.currentTimeMillis()
    val ret = Fibonacci().fibonacciSeriesRecursive(30)
    print("\n$ret\n")
    val endTimeRec = System.currentTimeMillis()

    val startTime2 = System.currentTimeMillis()
    val ret2 = Fibonacci().fibonacciSeriesRecursiveMemoization(30)
    print("\n$ret2\n")
    val endTimeDp = System.currentTimeMillis()


    println("Recursive stack: ${endTimeRec - startTime1} millisec")
    println("Dp: ${endTimeDp - startTime2} millisec") // fibonacci without dp is almost not runnable for index > 30
}