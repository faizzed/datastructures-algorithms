package algorithms.graphs

/*
* Hamilton Path/Cycle in a Graph
* ------------------------------
* Hamilton graph is such where we can visit all vertices exactly once.
* 1. If a path starts and ends at the same vertex, its a cycle
* 2. If a path does not starts and end at the same vertex its a path
*
* Implementation is as follows:
* 1. use dfs
* 2. If a node is encountered where the surrounding edges are all visited but there are still edges to visit then its a false path
*    remove that node from path list. Dfs will traceback and start at the next discoverable node.
* 3. If we have visited all unvisited nodes in a graph then its a path or cycle.
* 4. if visited list is equals graph length then we know we have visited all vertices.
* 5. Dfs will explore all possible paths in the graph there the its O(v*e)
* */
class HamiltonCycleGraph(val graph: Map<Int, List<Int>>) {
    val visited = mutableSetOf<Int>()

    fun findPaths(src: Int = graph.entries.first().key) {
        visited.add(src)

        if (visited.size == graph.size) {
            if (graph[graph.entries.first().key]!!.contains(src)) { // this is to detect the cycle but it will only work if the root is considered first node of graph in map.
                println("Cycle: ${visited.joinToString(" --> ")}")
            } else {
                println("Path : ${visited.joinToString(" --> ")}")
            }
        }

        for (edge in graph[src]!!) {
            if (!visited.contains(edge)) {
                findPaths(edge)
            }
        }
        visited.remove(src)
    }
}

fun main() {
    /*
    *  0 -- 3 --- 4
    *  |    |    |  \
    *  1 -- 2 -- 5 - 6
    *
    *
    * */
    mapOf(
        0 to listOf(1, 3),
        1 to listOf(0, 2),
        2 to listOf(1, 5, 3),
        3 to listOf(0, 2, 4),
        4 to listOf(3, 5, 6),
        5 to listOf(2, 4, 6),
        6 to listOf(4, 5),
    ).also {
        HamiltonCycleGraph(it).apply {
            findPaths()
        }
    }

    println("---------")
    mapOf(
        0 to listOf(1, 2),
        1 to listOf(0, 3),
        2 to listOf(0, 3, 4),
        3 to listOf(1, 2, 4),
        4 to listOf(2, 3),
    ).also {
        HamiltonCycleGraph(it).apply {
            findPaths()
        }
    }
}