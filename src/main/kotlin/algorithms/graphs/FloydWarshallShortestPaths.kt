package algorithms.graphs

data class FloydWarshallVertex(val name: String) {
    override fun toString(): String {
        return name
    }
}

data class FloydWarshallEdge(val name: String, val weight: Double) {
    override fun toString(): String {
        return "$name($weight)"
    }
}

/**
 * floyd marshall algorithm works the same as bellman ford and dijkstra with the diff being it finds the shortest paths from all nodes to all its connecting nodes
 * - it works with negative weighted edges too
 * - Again the data structure is important to get this right..
* */
class FloydWarshall(vertices: List<Pair<FloydWarshallVertex, List<FloydWarshallEdge>>>) {

    val graph = mutableMapOf<String, MutableMap<String, Double>>()

    init {
        // init the grid with 0 diagonally
        // infinity for edges not connected
        // values for edges connected..

        // another way could be
        // just recieve the following data as input
        //     int graph[][] = { { 0, 3, INF, 5 }, { 2, 0, INF, 4 }, { INF, 1, 0, INF }, { INF, INF, 2, 0 } };
        // 0 for same nodes
        // values for connected edges
        // INF for non connected edges

        // here we are only trying to make the above table.
        for (vertexI in vertices) {
            for (vertexJ in vertices) {
                if (!graph.containsKey(vertexI.first.name)) {
                    graph[vertexI.first.name] = mutableMapOf()
                }

                if (vertexI.first == vertexJ.first) {
                    graph[vertexI.first.name]!![vertexJ.first.name] = 0.0
                } else {
                    graph[vertexI.first.name]!![vertexJ.first.name] = Double.POSITIVE_INFINITY // Int.MAX_VALUE may overflow to signed if we added more into it.
                }
            }
        }

        vertices.forEach { pair ->
            pair.second.forEach {
                graph[pair.first.name]?.set(it.name, it.weight)
            }
        }
    }


    fun shortestPaths() {
        for (k in graph) {
            for (i in graph) {
                for (j in graph) {
                    /*
                    * How to understand these loops?
                    * - i is row
                    * - j is column
                    * - k= row, column.. depending on which index is first is the row and the second is column
                    * - We take k because we want to find the shortest distance from
                    *   each node to all other nodes (direct connecting or indirect connecting) the times as much
                    *   as there are nodes in the graph
                    *   That's when the optimized short distance is reached.
                    *   If there are no negative cycles ofcourse.
                    */
                    val ij = graph[i.key]!![j.key]!!
                    val ik = graph[i.key]!![k.key]!!
                    val kj = graph[k.key]!![j.key]!!
                    val shortestPath = ik + kj
                    if ( shortestPath < ij) {
                        graph[i.key]!![j.key] = shortestPath
                    }
                }
            }
        }
    }

    override fun toString(): String {
        graph.forEach { (t, u) ->
            println("$t: $u")
        }
        return ""
    }
}

fun main() {

    /*
    * Check graph at resources/diagrams.
    * */

    val vertices = listOf(
        Pair(FloydWarshallVertex("1"), listOf(FloydWarshallEdge("3", -2.0))),
        Pair(FloydWarshallVertex("2"), listOf(FloydWarshallEdge("1", 4.0), FloydWarshallEdge("3", 3.0))),
        Pair(FloydWarshallVertex("3"), listOf(FloydWarshallEdge("4", 2.0))),
        Pair(FloydWarshallVertex("4"), listOf(FloydWarshallEdge("2", -1.0))),
    )

    FloydWarshall(vertices).apply {
        also(::println)
        shortestPaths()
        println("\n")
        also(::println)
    }

    val vertices2 = listOf(
        Pair(FloydWarshallVertex("1"), listOf(FloydWarshallEdge("3", -2.0))),
        Pair(FloydWarshallVertex("2"), listOf(FloydWarshallEdge("1", 4.0), FloydWarshallEdge("3", 3.0), FloydWarshallEdge("4", -1.0))),
        Pair(FloydWarshallVertex("3"), listOf(FloydWarshallEdge("4", 2.0))),
    )

    FloydWarshall(vertices2).apply {
        also(::println)
        shortestPaths()
        println("\n")
        also(::println)
    }
}