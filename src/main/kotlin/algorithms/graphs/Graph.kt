package algorithms.graphs

import java.util.*

data class Edge<T>(var src: T, var dest: T, val weight: Int, var visited: Boolean = false) {
    override fun toString(): String {
        return "($dest, $weight)"
    }
}

open class Graph<V> {
    val graph = mutableMapOf<V, LinkedList<Edge<V>>>()

    fun addEdges(edges: List<Edge<V>>, sorted: Boolean = false) {
        edges.forEach {
            if (!graph.containsKey(it.src)) {
                graph[it.src] = LinkedList()
            }

            graph[it.src]!!.add(it)
            if (sorted) {
                graph[it.src]!!.sortBy { it.weight }
            }
        }
    }
}