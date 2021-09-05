package algorithms.graphs

import java.util.*

/**
 * Kosaraju algorithm finds strongly connected components inside a graph
 * What are strongly connected components?
 * 1. Inside a strongly connected components every vertex is visitable by every other vertex inside the component.
 * 3. There may be multiple strongly connected components inside a graph.
 *
 * How to find them?
 * 1. Visit the graph to the end via dfs, and while tracing back add the vertices in a top down fashion to a stack
 * 2. Reverse the graph now
 * 3. Get the first item from stack, make it a root of the new graph, start dfs, if there were multiple components you will end up with exploring just a part of the graph with dfs
 * 4. Mark that as one component and pop another one, as long as it wasnt part of or visited in the last call, start dfs again, and so on....
 *
 * This gives us parts of strongly connected components inside a graph/network
 *
 * Where is it used?
 *
* */
class KosarajuStronglyConnectedComponents(var graph: Map<Int, List<Int>>) {

    val stack = Stack<Int>()
    var visited = mutableListOf<Int>()
    var ssc = mutableListOf<Int>()

    fun stronglyConnectedComponents() {
        dfs() // make a top down graph stack
        reverseGraph() // make the reverse graph
        while (stack.isNotEmpty()) {
            dfs(stack.pop(), true)
            for (edge in visited) {
                stack.remove(edge) // we shouldn't traverse the entire subgraph again..
            }
            println(ssc)
            ssc = mutableListOf()
        }
    }

    /*
    * To reverse a graph, a parent becomes a child
    * In associated list, make list edges as parents and parent keys as list edges
    * */
    fun reverseGraph() {
        val reverseGraph = mutableMapOf<Int, MutableList<Int>>()
        for (vertex in graph) {
            for (edge in vertex.value) {
                if (!reverseGraph.containsKey(edge)) reverseGraph[edge] = mutableListOf()
                reverseGraph[edge]!!.add(vertex.key)
            }
        }
        graph = reverseGraph
    }
    /**
     * @param root Int start node in the graph
     * @param reverse bool is it the reverse dfs?
    */
    fun dfs(root: Int = 0, reverse: Boolean = false) {
        visited.add(root)
        if (graph.containsKey(root)) {
            for (vertex in graph[root]!!) {
                if (!visited.contains(vertex)) dfs(vertex, reverse)
            }
        }

        if (reverse) ssc.add(root)
        else {
            if (!stack.contains(root)) stack.push(root)
            visited.remove(root) // we need a clean visited list for the next call.
        }
    }
}

fun main() {
    mapOf(
        0 to listOf(1),
        1 to listOf(2),
        2 to listOf(0, 3),
        3 to listOf(4),
        4 to listOf(5, 7),
        5 to listOf(6),
        6 to listOf(4, 7)
    ).also { KosarajuStronglyConnectedComponents(it).apply {
        stronglyConnectedComponents()
    } }
}