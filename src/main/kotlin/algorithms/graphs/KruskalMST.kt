package algorithms.graphs


data class Edge(var src: Int, var dest: Int, val weight: Int)
data class Subset(var key: Int, var parent: Int?, var rank: Int)

class KruskalMST(val edges: MutableList<Edge>) {
    val subsets = MutableList<Subset>(edges.size) {
        Subset(edges[it].src, it, 0)
    }

    val result = mutableListOf<Edge>()

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
            Edge(0, 1, 2),
            Edge(0, 3, 3),
            Edge(1, 2, 5),
            Edge(2, 4, 9),
            Edge(2, 3, 4),
            Edge(4, 5, 11),
            Edge(4, 3, 6),
            Edge(4, 6, 12),
            Edge(5, 6, 10),
        )
    ).apply {
        result.forEach {
            println(it)
        }
    }
}