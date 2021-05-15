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

    /**
     * A naive algorithm is simple and easy to implement but inefficient in terms of time and memory.
     * In case of shuffling an arr (cards, roulette wheel, rolling dice) it produces a biased probability of events.
     *
     * Probability and randomness are closely related. If something is not random and does not have unknowns then its certain.
     * If something is random and have unknown events then we can try to know the possibility of all the things that may happen
     * and the one or many things that we want to happen.
     *
     * Not so simple but rolling a dice, s={1,2,3,4,5,6}, 6 possibilities, lets say we want 3
     * that is 1/6=0.16 or 16%
     *
     * But with the naive algorithm some elements may not have 16% probability each.
    * */
    fun naive() {
        val mapOfEvents = mutableMapOf<String, Int>()

        // we can change our problem to say, we want each element to have 16% probability to be at position 1
        // we should count how this happen over time
        for (i in 1..1000000) {
            val arr = intArrayOf(1,2,3)
            arr.forEachIndexed { i, e ->
                val k = Random().nextInt(arr.size)
                val temp = arr[k]
                arr[k] = arr[i]
                arr[i] = temp
            }

            val combination = arr.toList().joinToString("")

            if (mapOfEvents.containsKey(combination)) {
                mapOfEvents[combination] = mapOfEvents[combination]!! + 1
            } else {
                mapOfEvents[combination] = 1
            }
        }

        // it seems skewed enough to me
        print("Naive::\n")
        mapOfEvents.forEach { t, u ->
            print("$t: $u\n")
        }
    }

    /**
    * Moment of truth for the unbiased approach
    */
    fun unbiased() {
        val mapOfEvents = mutableMapOf<String, Int>()

        // we can change our problem to say, we want each element to have 16% probability to be at position 1
        // we should count how this happen over time
        for (i in 1..1000000) {
            val arr = intArrayOf(1,2,3)

            for (i in arr.size-1 downTo 0) {
                val j = Random().nextInt(i + 1)
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }

            val combination = arr.toList().joinToString("")

            if (mapOfEvents.containsKey(combination)) {
                mapOfEvents[combination] = mapOfEvents[combination]!! + 1
            } else {
                mapOfEvents[combination] = 1
            }
        }

        // it seems skewed enough to me
        print("Fisher Yates::\n")
        mapOfEvents.forEach { t, u ->
            print("$t: $u\n")
        }
    }
}

fun main() {
    FisherYatesShuffle().naive()
    FisherYatesShuffle().unbiased()
}