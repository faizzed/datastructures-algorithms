/**
 * Power set:
 * Creates subsets of a set
 * e.g s={a,b,c}
 * {}, {a} ... {a,b,c}
 * number of subsets is 2^n
*/
package algorithms.sets

import algorithms.math.bitmanipulation.fastPower
import misc.toBinaryString

class PowerSet {
    /**
     * Suppose s={a,b}
     * subsets {}, {a}, {b}, {a,b}
     * If we consider binary representation of indexes
     * 0: 00
     * 1: 01
     * 2: 10
     * 3: 11
     * This will yield the power set we want
    */
    fun bitWise(arr: Array<String>): MutableSet<MutableSet<String>> {
        val iterations = 2.fastPower(arr.size)
        val subsets = mutableSetOf<MutableSet<String>>()
        for (i in 0 until iterations) {
            val binaryArr = i.toBinaryString(arr.size).toCharArray()
            val childSet = mutableSetOf<String>()
            for (j in binaryArr.indices) {
                if (binaryArr[j] != '0') {
                    childSet.add(arr[j])
                }
            }
            subsets.add(childSet)
        }
        return subsets
    }
}

fun main() {
    PowerSet().bitWise(arrayOf("0", "1")).also(::println)
    PowerSet().bitWise(arrayOf("1", "2", "3")).also(::println)
    PowerSet().bitWise(arrayOf("a", "b", "c", "d")).also(::println)
}