package algorithms.graphs

import java.util.*

data class BellmanFordVertex(val name: String) {
    override fun toString(): String {
        return name
    }
}
data class BellmanFordEdge(val name: String, val weight: Int) {
    override fun toString(): String {
        return "$name($weight)"
    }
}

/**
 * Bellman ford algorithm also finds the shortest paths from node x to all its connecting nodes
 * It works similar to Dijkstra algorithm but there are two main points
 * - it takes more time to calculate the paths because it has to traverse all the nodes n-1 times
 * - it works with negative weighted edges too
 * - Again the data structure is important to get this right..
 * - One drawback is negative cycles, if there are negative cycles in the graph- bellman ford algorithm will
 *      not work.
* */
class BellmanFord {

    val graph = mutableMapOf<BellmanFordVertex, LinkedList<BellmanFordEdge>>()

    fun addEdges(vertex: BellmanFordVertex, edges: List<BellmanFordEdge>) {
        if (!graph.containsKey(vertex)) {
            graph[vertex] = LinkedList()
        }

        edges.forEach {
            graph[vertex]!!.add(it)
        }

    }

    val distances = mutableMapOf<String, Int>()

    fun shortestPath(vertex: String) {
        for (node in graph) {
            distances[node.key.name] = Int.MAX_VALUE
        }

        distances[vertex] = 0
        var distancesChange: Boolean

        mainLoop@for (i in 0..graph.size) {
            distancesChange = false
            for (node in graph) {
                for (edge in node.value) {
                    if (distances[node.key.name]!! + edge.weight < distances[edge.name]!!) {
                        distances[edge.name] = distances[node.key.name]!! + edge.weight
                        distancesChange = true
                    }
                }
            }
            // if at the end of each iteration there were no changes in distance, we have already
            // reached the shortest paths between nodes
            // we can quit here..
            if (!distancesChange) {
                break@mainLoop
            }
        }

        // look for negative weight cycles.
        // in case of negative weight cycles bellman ford algorithm will still relax the distances
        // after n-1 iterations, this will go on for infinite amount of time
        // that's why in this case bellman ford algorithm will not work
        for (node in graph) {
            for (edge in node.value) {
                if (distances[edge.name]!! > distances[node.key.name]!! + edge.weight) {
                    throw Exception("Negative cycle detected. Algorithm will not work..")
                }
            }
        }
    }
}

fun main() {
    BellmanFord().apply {
        addEdges(BellmanFordVertex("S"),
            listOf(
                BellmanFordEdge("A", 10),
                BellmanFordEdge("E", 8),
            )
        )

        addEdges(BellmanFordVertex("E"),
            listOf(
                BellmanFordEdge("D", 1),
            )
        )

        addEdges(BellmanFordVertex("D"),
            listOf(
                BellmanFordEdge("C", -1),
                BellmanFordEdge("A", -4),
            )
        )

        addEdges(BellmanFordVertex("C"),
            listOf(
                BellmanFordEdge("B", -2),
            )
        )

        addEdges(BellmanFordVertex("B"),
            listOf(
                BellmanFordEdge("A", 1),
            )
        )

        addEdges(BellmanFordVertex("A"),
            listOf(
                BellmanFordEdge("C", 2),
            )
        )

        shortestPath("S")
        println(distances)
    }
    // lets try one with negative cycle
    BellmanFord().apply {
        addEdges(BellmanFordVertex("A"),
            listOf(
                BellmanFordEdge("B", 4),
                BellmanFordEdge("C", 5),
            )
        )

        addEdges(BellmanFordVertex("C"),
            listOf(
                BellmanFordEdge("D", 3),
            )
        )

        addEdges(BellmanFordVertex("D"),
            listOf(
                BellmanFordEdge("B", -10),
            )
        )

        addEdges(BellmanFordVertex("B"),
            listOf(
                BellmanFordEdge("C", 5),
            )
        )

        shortestPath("A")
        println(distances)
    }
}