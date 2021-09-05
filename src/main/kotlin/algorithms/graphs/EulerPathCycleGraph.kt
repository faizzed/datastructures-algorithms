package algorithms.graphs

/**
* What is Euler-ian Path/Cycle?
*
* Consider the example:
* Your task is to deliver mail to the inhabitants of a city. For this reason, you want to find a route whose starting and ending point are the post office, and that goes through every street exactly once.
*
* It has the following properties
* 1. Visit all the edges exactly once.
* 2. Cycle starts and ends at the same vertex.
* 3. Path doesnt start/end at the same vertex although it visits all edges.
*
* How to detect if a graph is such a graph?
* 1. If all vertices has even edges - its a cycle
* 2. If vertices doesnt have all even edges, but still has 2 odd edges vertices, its a path
* 3. This can be done simply by just counting the adjacency list but we also need to know if they are actually connected. Use DFS to visit all.
*
* The best resources to learn from
* https://cp-algorithms.com/graph/euler_path.html
* https://www.geogebra.org/m/t3cseNsD
* And the best of all https://graphonline.ru/en/
* Try to make a graph with these properties and run the path/cycle algorithm.
* */
class EulerPathCycleGraph(val graph: Map<Int, List<Int>>) {
    private val connectedGraph = mutableMapOf<Int, MutableList<Int>>()
    var oddDegreeNodes = 0

    /*
    * If graph is not connected then its not an euler graph
    * If graph has degree of odds more then 2 then it means all vertices does not have even edges, its not a cycle
    * If graph has
    * */
    fun findPathCycle(): String {
        if (!isConnected()) {
            return "Not an Euler graph."
        }

        if (oddDegreeNodes == 2) {
            return "Euler Path Graph."
        }

        if (oddDegreeNodes == 0) {
            return "Euler Cycle Graph"
        }


        return "Not an Euler graph."
    }

    /**
     * Using dfs we will check if the graph is connected
     * If there are vertices with list size greater then zero and they arent visited then the graph is not connected.
    * */
    private fun isConnected(): Boolean {
        if (graph.isEmpty()) {
            throw Exception("Graph is empty.")
        }

       dfs(graph.entries.first().key)
        for (node in graph.entries) {
            // also keep track of odd degree nodes here.
            if (node.value.size % 2 !== 0) {
                oddDegreeNodes++
            }
            if (graph[node.key]!!.size != connectedGraph[node.key]!!.size) {
                return false
            }
        }

        return true
    }

    private fun dfs(vertex: Int) {
        if (!connectedGraph.contains(vertex)) {
            connectedGraph[vertex] = mutableListOf()
        }

        for (edge in graph[vertex]!!) {
            if (connectedGraph[vertex]!!.contains(edge)) {
                continue
            }
            connectedGraph[vertex]!!.add(edge)
            dfs(edge)
        }
    }
}

fun main() {
    val graph = mapOf(
        1 to listOf(0, 2),
        2 to listOf(0, 1),
        0 to listOf(1, 2, 3),
        3 to listOf(0, 4),
        4 to listOf(3)
    )

    EulerPathCycleGraph(graph).apply {
        findPathCycle().also(::println)
    }

    val graph2 = mapOf(
        1 to listOf(0, 2),
        2 to listOf(0, 1),
        0 to listOf(1, 2, 3, 4),
        3 to listOf(0, 4),
        4 to listOf(3, 0)
    )

    EulerPathCycleGraph(graph2).apply {
        findPathCycle().also(::println)
    }

    val graph3 = mapOf(
        1 to listOf(0, 2, 3),
        2 to listOf(0, 1),
        0 to listOf(1, 2, 3),
        3 to listOf(0, 1, 4),
        4 to listOf(3)
    )

    EulerPathCycleGraph(graph3).apply {
        findPathCycle().also(::println)
    }

    val graph4 = mapOf(
        1 to listOf(0, 2),
        2 to listOf(0, 1),
        0 to listOf(1, 2),
    )

    EulerPathCycleGraph(graph4).apply {
        findPathCycle().also(::println)
    }
}