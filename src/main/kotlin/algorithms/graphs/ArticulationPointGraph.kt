package algorithms.graphs

import kotlin.math.min
/**
 * Bridges and articulation points in a graph are exactly the same.
 * The algorithm to find them is same as well.
 *
 * **NOTE**: Use the algorithm in BridgesInGraph.kt, thats much more understandable
 * Remove the concept of childern - not important.
 *
 * Articulation Point:
 * ------------------
 *
 * This is an edge where by removing it or by its failure or dis-connectivity the graph/network break into two components.
 * This is used as a problem for cities, roads, networks or maybe just about anything where one set of net is connected to another set of net
 * via a single edge, which makes it a critical edge in that network.
 *
 * How to find these edges?
 * -----------------------
 * 1. If there is a child which doesnt have a back edge to its ancestor(s), by removing that link the connectivity breaks. Backedge is an extra link to the ancestor nodes.
 * 2. To know an edge is a backedge is if its low is greater then its parent discovery - Low should be less then disc of parent to indicate that there is a backedge
 *         1
 *         |
 *         2
 *        /\
 *       3-4
 *
 *       2 is a bridge - its low is 1 and its parent disc is 0
 *       imagine another graph
 *         1
 *         |
 *         2
 *        /\
 *       3 4
 * All of them are bridges.
 *
 * Things to note:
 * 1. Dont update low with direct parent low.
 * 2. If an edge is visited but not parent, update path with its ancestor path
 * 3. Update parent path with its childs backedge path, if there was.
 *
 * In the above graph none of the properties exists.
 * In the graph above above this one, 4 has a backedge to 2, it inherits its path, 3 update 4s path. SO they become less then their parents discovery and they remain non bridges.
 * Although 2 doesnt inherit anything, has no back edges and its low remain higher then its parent disc, it makes a bridge.
 *
 * */
class ArticulationPointGraph(val graph: Map<Int, List<Int>>) {
    var time = 0
    val visited: MutableList<Int> = mutableListOf()
    val discovered: MutableMap<Int, Int> = mutableMapOf()
    val low: MutableMap<Int, Int> = mutableMapOf()
    val points: MutableList<Int> = mutableListOf()

    fun findPoints(): MutableList<Int> {
        for (vertex in graph.keys) {
            if (!visited.contains(vertex)) {
                dfs(vertex, -1)
            }
        }

        return points
    }

    private fun dfs(vertex: Int, parent: Int) {
        var children = 0
        visited.add(vertex)
        low[vertex] = time++
        discovered[vertex] = time++

        for (connectedVertex in graph[vertex]!!) {
            if (!visited.contains(connectedVertex)) {
                children++
                dfs(connectedVertex, vertex)
                low[vertex] = min(low[vertex]!!, low[connectedVertex]!!)
                if (parent != -1 && low[connectedVertex]!! >= discovered[vertex]!!) {
                    points.add(vertex)
                }
            } else if (connectedVertex != parent) {
                low[vertex] = min(low[vertex]!!, discovered[connectedVertex]!!)
            }
        }

        if (parent == -1 && children > 1) {
            points.add(vertex)
        }
    }
}

fun main() {
    ArticulationPointGraph(mapOf(
        0 to listOf(1, 2, 3),
        1 to listOf(0, 2),
        2 to listOf(0, 1),
        3 to listOf(0, 4),
        4 to listOf(3)
    )).apply {
        findPoints()
        println(points)
    }
}
