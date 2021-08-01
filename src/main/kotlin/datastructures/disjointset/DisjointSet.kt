package datastructures.disjointset

/**
 * Disjoint set:
 *
 *
 * This description is totally wrong whichever site you got it from!!!
 * Check the visualization here https://www.cs.usfca.edu/~galles/visualization/DisjointSets.html
 *
 * A collection of disjoint (non-overlapping) sets
 * a={1,2,3,4,5}
 * a={{1,2,3,4}, {5}}
 *
 * If we are already given a tree or graph and we have some parent child relation then good.
 *
 * Otherwise each input array elements has no relation with each other and they are trees on their own.
 *
 * If we start unionising them, we will get trees such as
 * e.g
 * initial
 *
 * 1 2 3 4 5
 *
 * union(4, 3)
 *
 *  1 2 3 5
 *       \
 *        4
 *
 * union(2, 1)
 *
 *       1   3  5
 *      /     \
 *     2       4
 *
 * union(1, 3)
 *
 *           3     5
 *          /\
 *         1  4
 *        /
 *       2
 *
 * Operations include adding new sets, merging new sets, finding whether two elements are in the same set.
 *
 */
class DisjointSet {
    private val parent = mutableMapOf<Int, Int?>()

    /**
     * We create sets from the input array
     * Each set is dis-joint and doesnt have any relation with other sets
    * */
    fun makeSet(elements: IntArray) {
        for (i in elements) {
            parent[i] = null
        }
    }

    /**
     * Find the root of the set in which element `k` belongs
     * We can union two sub-trees or sets by just attaching their roots, or we can see if one sets size is greater then another etc.
     *
     * In a union find set, or the data structure we have, every element will have its parent stored alongside it.
     * We can recurse the element upwards until we get to the element where its root is equal to the value it has..
     * */
    fun find(k: Int): Int {
        return if (parent[k] == null) {
            k
        } else {
            find(parent[k]!!)
        }
    }

    /**
    * if we need to merge two elements, we need to know which sets they are in..If they are in the same set
     * fine! they will share the same parent.
     *
     * If they are in two disjoint sets, we will need to attach the second set to the first.
     *
     * The attachment can be based on a certain condition like whats the size of set a compared to b etc..
    * */
    fun union(a: Int, b: Int) {
        parent[find(a)] = find(b)
    }

    /**
     * Check if two elements belongs to the same set
     * Another way -- check if they have the same parent..
    * */
    fun connected(a: Int, b: Int): Boolean {
        return find(a) == find(b)
    }

    override fun toString(): String {
        return parent.toString()
    }
}

fun main() {
    val l = intArrayOf(1, 2, 3, 4, 5)
    DisjointSet().apply {
        makeSet(l)
        union(4,3)
        union(4,2)
        union(2, 1)
        union(1, 3)
        also(::println)
//        find(2).also(::println)
//        connected(3, 4).also(::println)
//        connected(1, 4).also(::println)
//        connected(3, 5).also(::println)
    }

}

