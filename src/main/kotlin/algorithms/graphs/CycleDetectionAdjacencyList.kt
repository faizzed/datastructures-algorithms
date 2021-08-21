package algorithms.graphs

import java.util.*

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
        for (node in graph) {
            hasCycle(mutableListOf(), node.key).let {
                if (it) { println("Node: ${node.key} creates a cycle..") }
            }
        }
    }

    private fun hasCycle(visited: MutableList<String>, node: String): Boolean {

        if (visited.contains(node)) {
            return true
        }

        visited.add(node)

        if (graph.containsKey(node)) {
            for (edge in graph[node]!!) {
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