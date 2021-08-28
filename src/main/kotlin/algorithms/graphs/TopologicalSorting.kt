package algorithms.graphs

import java.util.*

/**
 * Order criterion:
 * ---------------
 * 1. Vertices with zero or min outgoing edges first
 * 2. Vertices with max ingoing edges last
 * 3. Vertices with outgoing edges before vertices with ingoing edge
 *
 * Useful in scheduling jobs with dependencies
 * Works with directed graphs with no cycles aka DAG
 * Vertices (jobs) with 0 in order degree are first in order
 * Vertices (jobs) with greater in order degree are last in order
 * Vertices must appear in order of outgoing edges
 *          job a trigger job b
 *          a --- b
 *          (a,b)
 *          [a,b]
* */
class TopologicalSorting: Graph<Int>() {
    val stack = Stack<Int>()
    val visited = mutableListOf<Int>()

    fun sort() {
        // some vertices may only have outgoing edges
        // they will be missed if we start to explore the graph from a single vertex
        // therefore we will run all the vertices through dfs if they arent visited
        for (vertex in graph) {
            if (!visited.contains(vertex.key)) {
                dfs(vertex.key)
            }
        }
    }

    // important to note
    // add to stack on backtrack -- this signifies the lowest number of outgoing edges
    private fun dfs(vertex: Int) {
        visited.add(vertex)
        if (graph[vertex] != null) {
            for (edge in graph[vertex]!!) {
                dfs(edge.dest)
            }
        }
        if (!stack.contains(vertex)) stack.add(vertex)
    }
}

fun main() {
    TopologicalSorting().apply {
        listOf(
            Edge(5, 2, 0),
            Edge(5, 0, 0),
            Edge(0, 3, 0),
            Edge(0, 2, 0),
            Edge(3, 1, 0),
            Edge(4, 2, 0),
            Edge(4, 1, 0),
        ).also {
            addEdges(it, true)
        }

        println(graph)
        sort()
        println(stack) // the stack needs to be reversed to get the order desired.
    }
}