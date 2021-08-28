package algorithms.graphs

import kotlin.math.min

/**
 * Bridges and articulation points in a graph are exactly the same.
 * The algorithm to find them is same as well.
 *
 * Bridge:
 * ------
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
 * Links to further reading https://cp-algorithms.com/graph/bridge-searching.html#toc-tgt-1
 * https://www.youtube.com/watch?v=ECKTyseo2H8
* */
class BridgesInGraph(val graph: Map<Int, List<Int>>) {
    var time = 0
    val visited: MutableList<Int> = mutableListOf()
    val discovered: MutableMap<Int, Int> = mutableMapOf()
    val low: MutableMap<Int, Int> = mutableMapOf()
    val points: MutableList<Pair<Int, Int>> = mutableListOf()

    fun findBridges(): MutableList<Pair<Int, Int>> {
        for (vertex in graph.keys) {
            if (!visited.contains(vertex)) {
                dfs(vertex)
            }
        }

        return points
    }

    private fun dfs(vertex: Int, parent: Int = -1) {
        visited.add(vertex)
        low[vertex] = time++
        discovered[vertex] = low[vertex]!!

        for (connectedVertex in graph[vertex]!!) {

            if (connectedVertex == parent) { // direct parent already visited -> do nothing
                continue
            }

            if (visited.contains(connectedVertex)) { // visited but not parent but an ancestor, update the low path with its back edge ancestor path
                low[vertex] = min(low[vertex]!!, discovered[connectedVertex]!!)
            } else {
                dfs(connectedVertex, vertex)
                low[vertex] = min(low[vertex]!!, low[connectedVertex]!!) // update parent low with its child back edge path if there was
                if (low[connectedVertex]!! > discovered[vertex]!!) {
                    points.add(Pair(vertex, connectedVertex))
                }
            }
        }
    }
}

fun main() {
    val g1 = mapOf(
        0 to listOf(1, 2, 3),
        1 to listOf(0, 2),
        2 to listOf(0, 1),
        3 to listOf(0, 4),
        4 to listOf(3)
    )

    BridgesInGraph(g1).apply {
        findBridges()
        println(points)
    }

    val g2 = mapOf(
        1 to listOf(2),
        2 to listOf(3, 4),
        3 to listOf(2, 4),
        4 to listOf(2, 3),
    )

    BridgesInGraph(g2).apply {
        findBridges()
        println(points)
    }

    val g3 = mapOf(
        1 to listOf(2),
        2 to listOf(3, 4),
        3 to listOf(2),
        4 to listOf(2),
    )

    BridgesInGraph(g3).apply {
        findBridges()
        println(points)
    }
}
