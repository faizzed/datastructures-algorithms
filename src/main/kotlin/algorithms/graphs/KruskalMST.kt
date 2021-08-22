package algorithms.graphs


data class KruskalEdge(var src: Int, var dest: Int, val weight: Int)
data class Subset(var key: Int, var parent: Int?, var rank: Int)

/**
 * Kruskal's Algorithm
 * 1. Sort the edges by weight
 * 2. Start picking the edges in desc order, doesnt matter if they are from the visited nodes or not, also check that an edge doesnt form a cycle
 *      Cycles are checked using Union find, where we find the parent of each node and if parent is the same it forms a cycle
 *      We union edges first by union-ising each edge with another
 *      Or same edge src and dest
 * 3. Pick all the edges that doesnt form a cycle in desc order and thats the Kruskal MST
* */
class KruskalMST(val edges: MutableList<KruskalEdge>) {
    val subsets = MutableList<Subset>(edges.size) {
        Subset(edges[it].src, it, 0)
    }

    val result = mutableListOf<KruskalEdge>()

    init {
        for (e in edges) {
            union(e.src, e.dest).also {
                if (!it) {
                    result.add(e)
                }
            }
        }
    }

    /**
     * Find the parent of i in sets and return
     * */
    fun find(i: Int): Int {
        // this is the only item in set
        if (subsets[i].parent == i) {
            return i
        } else {
            // if there are many items in the set then pass the parent of this element until we find the root
            return find(subsets[i].parent!!)
        }
    }

    /*
    * Join two elements into one set
    * We find the parents of both elements and attach them to each other
    *
    * Ranking is important because if we keep on adding children to a prent it will create long chain of
     * linear list to traverse and we will have O(n) time complexity
     *
     * With rank, we assign rank to each element
     * Higher rank becomes the parent
     * With equal ranks,one become parent of another and the prior increases its rank
     * In this case we achieve O(log n) of time
    * */
    fun union(a: Int, b: Int): Boolean {
        val x = find(a)
        val y = find(b)

        if (x==y) {
            return true
        }

        if (subsets[a].rank < subsets[b].rank) {
            subsets[a].parent = y
        }
        else if (subsets[a].rank > subsets[b].rank) {
            subsets[b].parent = x
        }
        else {
            subsets[b].parent = x
            subsets[a].rank++
        }

        return false
    }
}

fun main() {
    KruskalMST(
        mutableListOf(
            KruskalEdge(0, 1, 2),
            KruskalEdge(0, 3, 3),
            KruskalEdge(1, 2, 5),
            KruskalEdge(2, 4, 9),
            KruskalEdge(2, 3, 4),
            KruskalEdge(4, 5, 11),
            KruskalEdge(4, 3, 6),
            KruskalEdge(4, 6, 12),
            KruskalEdge(5, 6, 10),
        )
    ).apply {
        result.forEach {
            println(it)
        }
    }
}