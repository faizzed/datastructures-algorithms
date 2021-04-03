package datastructures.trees.fenwicktree

/**
 * Fenwick tree allows form efficient calculation of cumulative sum between certain indexs
 * it also allows for updating the tree in O(log (n)) time compared to linear array operations.
 *
 * Although it is called fenwick tree but its not a tree "tree", same as a heap it is implemented through arrays
 * Unlike heap which finds it children and parent through 2(i)+1, 2(i)+2 and (i-1)/2 it finds the parent elements
 * by the least significant 1 bit for an index
 *
 * @param tree - data starts at index 1, ar[0] is ignored
 * */
class FenwickTree(private val tree: IntArray) {

    /**
    * Class used to sum and unwind ranges..
    * */
    inner class SumQuery {
        fun apply(a: Int, b: Int): Int {
            return a+b
        }

        fun undo(c: Int, b: Int): Int {
            return c-b
        }
    }

    private val f = SumQuery()

    /**
     * The principle used is considering the least significant bit in the input array indexes.
     * If the least significant bits has 1 followed by zeros, then it will add the preceding values to it depending on
     * how many zeros it has after. The formula used is,
     * 2^(n of zeros)=sum of x preceding values
     * e.g index 4 is 0100, it will become 2^2=4, index4, index3, index2, index1 will be added.
     *
     * A more general example.
     *
     * 0 - 0, 0000
     * 1 - 5, 0001
     * 2 - 2, 0010
     * 3 - 9, 0011
     * 4 - -3, 0100
     * 5 - 5, 0101
     * 6 - 20, 0110
     * 7 - 10, 0111
     * 8 - -7, 1000
     * ...
     * index2 => 2^1=2 => 2+5
     * index4 => 2^3=8 => -3+9+2+5 => 13
     * index6 => 2^1=2 => 20+5=25
     * index8 => -7+all the preceding vals => 41
     *
     * The method i+(i&-i) will yield the indexes for summation that each index holds.
     */
    init {
        for (i in 1 until tree.size) {
            val p = i + (i and -i)
            if (p < tree.size) {
                tree[p] = f.apply(tree[p], tree[i])
            }
        }
    }

    fun query(i: Int): Int? {
        var i = i
        var q: Int? = null
        while (i > 0) {
            q = if (q == null) {
                tree[i]
            } else {
                f.apply(q, tree[i])
            }
            i -= i and -i
        }
        return q
    }

    /**
     * Get the prefix sum between a range
     * If index i is greater then 1 it means we need to unwind and subtract the values before index i from final sum.
     *
     * For example:
     * 0, 1, 3, 4, 2, 0, 1 => 0, 1, 4, 4, 10, 10, 1
     * sum(3, 4) = 6
     * if we look at our arr its 14
     * we need to unwind and get the summation before index 3 and subtract it form the final index sum.
     *
     * But this wont be an issue if the index starts at i <= 1
    * */
    fun query(i: Int, j: Int): Int? {
        return if (i > 1) {
            f.undo(query(j)!!, query(i - 1)!!)
        } else {
            query(j)
        }
    }

    /**
     * Why do we need to undo the last index from the given value?
     * Suppose we need to get the value at index 2
     * From our last discussion at making the fenwick tree we already replaced the array indices with prefix sum values
     * We need to unwind those prefix sums if we need to get the base value.
     *
     * Therefore index 2: 0010
     * means we added the index 1 into it.
     *
     * But even if the index was lets say 8
     * 1000 means we added last 8 indices into it..
     *
     * i will be passed to query method
     * which will add i=8 and at the next loop it will exit since 8-(8&-8) = 0
     * and then for i=7 we will get index7+index6+index4
     * index8 - (index7+index6+index4)
     * this will get us the initial base value that was stored at the passed array..
    * */
    fun valueAt(i: Int): Int {
        return f.undo(query(i)!!, query(i - 1)!!)
    }

    /**
     * Apply the difference k to the whole range starting from index i
     * the indexes changed with the difference are the same indexes we feeded during
     * creating the fenwick tree
     * same formula - i+(i&-i)
     *
    * */
    private fun apply(i: Int, k: Int) {
        var i = i
        val size = tree.size
        while (i < size) {
            tree[i] = f.apply(tree[i], k)
            i += i and -i
        }
    }

    /**
     * We find the difference between the update value with the original
     * and pass that to apply.
    * */
    fun update(i: Int, value: Int) {
        val orig = valueAt(i)
        apply(i, f.undo(value, orig))
    }

    override fun toString(): String {
        return tree.toList().toString()
    }
}

fun main() {
    val l = intArrayOf(0, 1, 3, 4, 2, 0, 1)

    FenwickTree(l).apply {
        also(::println)
        valueAt(3).also(::println)
        query(3, 4).also(::println)
        update(2, 2)
        query(1, 3).also(::println)
    }
}