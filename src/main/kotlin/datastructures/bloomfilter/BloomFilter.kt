package datastructures.bloomfilter

/**
 * A memory efficient and probabilistic data structure that finds whether an element is in set.
 * It can give false positives.
 * It can not give false negatives.
 *
 * It supports two operations, add, contains?
* */
class BloomFilter {

    private val bitSet = Array(5) { false }

    private fun h1(letter: String): Int {
        var sum = 0
        letter.toCharArray().forEach {
            sum += it.toInt()
        }
        return sum
    }

    private fun h2(letter: String): Int = 2 + h1(letter)
    private fun h3(letter: String): Int = 4 * h1(letter)

    private fun flipSetIndex(value: Int) {
        val index = value % bitSet.size
        bitSet[index] = true
    }

    private fun getSetIndex(value: Int): Boolean {
        val index = value % bitSet.size
        return bitSet[index]
    }

    fun add(letter: String) {
        flipSetIndex(h1(letter))
        flipSetIndex(h2(letter))
        flipSetIndex(h3(letter))
    }

    fun contains(letter: String): Boolean {
        return getSetIndex(h1(letter)) && getSetIndex(h2(letter)) && getSetIndex(h3(letter))
    }

    override fun toString(): String {
        return bitSet.toList().toString()
    }
}

fun main() {
    BloomFilter().apply {
        add("B")
        also(::println)
        contains("D").also(::println)
        contains("B").also(::println)
    }
}