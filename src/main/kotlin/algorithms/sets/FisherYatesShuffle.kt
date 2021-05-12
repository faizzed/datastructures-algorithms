package algorithms.sets

import java.util.*

/**
 * Algorithm for efficient and unbiased shuffling.
 * If a set is shuffled starting from 0 and randomly(0, n) switching it with a rand index within the arr, that creates a
 * biased set.
 * Biased set is when, some numbers have higher chances of occurance than others.
 *
 * This algorithm shuffles and then reduces the sample space
 * every iteration is rand(0, n-1)
 * for a set
 * s={a,b,c}, n=3
 * rand(0,3) - a
 * rand(0,2) - c
 * rand(0,1) - b
 * shuffled set = {a,c,b}
* */
class FisherYatesShuffle {
    // function taken from https://www.rosettacode.org/wiki/Knuth_shuffle#Java
    fun shuffle(arr: IntArray) {
        var n = arr.size

        while (n>0) {
            var k = Random().nextInt(n--)
            val temp = arr[k]
            arr[k] = arr[n]
            arr[n] = temp
        }

        println(arr.toList())
    }
}

fun main() {
    FisherYatesShuffle().shuffle(
        (1..10).toList().toIntArray()
    )
}