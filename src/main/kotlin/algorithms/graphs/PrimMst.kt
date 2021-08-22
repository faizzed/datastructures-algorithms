package algorithms.graphs


/**
 * Prim algorithm is similar to kruskal
 * But kruskal is a bit weird, it randomly start picking lowest edges from anywhere
 * In prim, we start at a particular vertex and see the smallest connected edge to another vertex.
 * Important to note: If we have visited more then one vertex, we will consider all the edges to get smallest from among the visited vertices
 *
*/
class PrimMst: Graph<String>() {
    val visited = mutableListOf<String>()
    val mst = mutableListOf<Edge<String>>()

    fun shortestPath(vertex: String) {
        visited.add(vertex)
        // find the next shortest path of all connected edges
        // from vertices in visited list
        val vortex = getShortest() ?: return
        // if it doesnt contains add, otherwise it creates a cycle, discard
        mst.add(vortex)
        shortestPath(vortex.dest)
    }

    /*
    * This is the meat of the algorithm
    * GO through all the visited vertices and sort their edges by desc order to pick the smallest one
    * But here is the catch - we dont want to go through the already picked vertices, in case the graph is large this will optimize traversal
    * And we dont want to pick the edges that may create a cycle - if the dest if already visited dont pick it because it will create a cycle
    * */
    private fun getShortest(): Edge<String>? {
        val edges = mutableListOf<Edge<String>>()
        for (vertex in visited) {
            if (graph[vertex] == null) {
                continue
            }
            val iterator = graph[vertex]!!.iterator()
            var index = 0
            while (iterator.hasNext()) {
                val edge = iterator.next()
                if (edge.visited) {
                    iterator.remove()
                } else if (visited.contains(edge.dest)) {
                    iterator.remove()
                } else {
                    edges.add(edge)
                }
                index++
            }
        }

        if (edges.isEmpty()) {
            return null
        }

        edges.sortBy { it.weight }
        return edges.first().apply {
            visited = true
        }
    }
}

fun main() {
    PrimMst().apply {
        listOf(
            Edge("a", "b", 2),
            Edge("a", "c", 3),
            Edge("a", "d", 3),

            Edge("b", "c", 4),
            Edge("b", "e", 3),

            Edge("c", "d", 5),
            Edge("c", "f", 6),
            Edge("c", "e", 1),

            Edge("d", "f", 7),

            Edge("f", "g", 9),
        ).also {
            addEdges(it, true)
        }

        println(graph)
        shortestPath("a")
        println(mst)
    }
}