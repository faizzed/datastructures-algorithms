package algorithms.graphs

import java.util.*

/**
* Cycle is when one or more vertexs form a cycle, where the a vertex is reachable more then one time from any other vertex
 *    c
 *  /  \
 * a -- b
 *
 * These vertexs form a cycle.
 *
 * Cycle are avoided in finding shortest path algorithms specially in case where we relax the paths.
 * They drop the path continuously in loop if there is a negative weight with any associated edge.
 *
 * To detect a cycle we either use union find, which attach childs to a group, you can check at the same
 * time if they are in a similar set, having a similar parent in that case it forms a cycle.
 * If not then the set is disjoint.
 *
 * But its a shitty way in my opinion, detecting cycle via adjacency list is much more understandable
* */
class CycleDetectionAdjacencyList {
    val graph = mutableMapOf<String, LinkedList<String>>()

    fun addEdges(edges: List<Pair<String, String>>) {
        edges.forEach {
            if (!graph.containsKey(it.first)) {
                graph[it.first] = LinkedList()
            }

            graph[it.first]!!.add(it.second)
        }
    }

    fun hasCycle() {
        for (vertex in graph) {
            hasCycle(mutableListOf(), vertex.key).let {
                if (it) { println("Node: ${vertex.key} creates a cycle..") }
            }
        }
    }

    /**
     * The basic idea is: start going deeper on an edge, and keep track of visiting vertices on that path
     * if at any point visiting the child edges along that path we visit an already visited vertex then there is a cycle
     * Union find is a drag..
    * */
    private fun hasCycle(visited: MutableList<String>, vertex: String): Boolean {

        if (visited.contains(vertex)) {
            return true
        }

        visited.add(vertex)

        if (graph.containsKey(vertex)) {
            for (edge in graph[vertex]!!) {
                if (hasCycle(visited, edge)) {
                    // end the loop because we found cycle..
                    return true
                }

                visited.remove(edge)
            }
        }

        return false
    }
}

fun main() {
    CycleDetectionAdjacencyList().apply {
        listOf(
            Pair("0", "1"),
            Pair("2", "1"),
            Pair("2", "3"),
            Pair("3", "4"),
            Pair("4", "0"),
            Pair("4", "2"),
        ).also {
          addEdges(it)
        }
        println(graph)
        hasCycle()
    }

    CycleDetectionAdjacencyList().apply {
        listOf(
            Pair("0", "1"),
            Pair("0", "2"),
            Pair("1", "2"),
            Pair("2", "0"),
            Pair("2", "3"),
            Pair("3", "3"),
        ).also {
          addEdges(it)
        }
        println(graph)
        hasCycle()
    }
}