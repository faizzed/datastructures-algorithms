package algorithms.math.prime

/**
 * List prime numbers upto n
 * The class uses Sieve of Eratosthenes algorithm
 *
 * How it works?
 * - create an array from [2....n]
 * - init all indexes with natural numbers in order
 * - start a pointer form index 0 and mark its multiple with false, this is to find composite numbers
 * - move pointer to index + 1, if its not false, mark all its multiples as false
 * - if we reach an index that is false - stop.
 * - filter non null and that's the prime numbers between 0-n
*/
class PrimeNumbersUptoN {
    fun sieveAlgorithm(limit: Int): MutableList<Int> {
        val list = (0..limit).toMutableList()

        loop1@ for (i in 2 until list.size-1){
            if (list[i] == 0) {
                continue@loop1
            }

            for (j in i..list.size/2) {
                if (i*j < list.size && list.get(i*j) != null) {
                    list[i*j] = 0
                }
            }
        }
        return trimAndReturn(list)
    }

    fun trimAndReturn(list: MutableList<Int>): MutableList<Int> {
        return list.subList(2, list.size-1).filter { it != 0 } as MutableList<Int>
    }
}

fun main() {
    PrimeNumbersUptoN().apply {
        sieveAlgorithm(100).also(::println)
    }
}