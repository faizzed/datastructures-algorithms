package algorithms.graphs

/*
* In a weighted graph, find a path starting and ending at the same vertex where it visit all the vertices with the shortest weight.
* Hamiltonian makes sense with weighted edges
* Dijskstra? We dont know if it will visit all edges
*
* The solution is based on hamiltonian path/cycle code
* */
class TravelingSalesmanProblem(val graph: Map<Pair<String, Int>, List<Pair<String, Int>>>) {
    val visited = mutableSetOf<String>()
    private val weights = mutableListOf<Int>()

    fun findPaths(src: Pair<String, Int> = graph.entries.first().key) {
        visited.add(src.first)
        weights.add(src.second)

        if (visited.size == graph.size) {
            val srcWeight = isCycle(graph.entries.first().key.first, src.first)
            if (srcWeight != null) {
                println("TSP: ${visited.joinToString(" --> ")} , Weight: ${weights.sum() + srcWeight}")
            }
        }

        for (edge in graph[Pair(src.first, 0)]!!) {
            if (!visited.contains(edge.first)) {
                findPaths(edge)
            }
        }
        visited.remove(src.first)
        weights.remove(src.second)
    }

    fun isCycle(src: String, dest: String): Int? {
        return graph[Pair(src, 0)]!!.filter {
            it.first == dest
        }.first().second
    }
}

fun main() {
    mapOf (
        Pair("A", 0) to listOf(Pair("B", 1), Pair("C", 4), Pair("D", 2)),
        Pair("B", 0) to listOf(Pair("A", 5), Pair("C", 3), Pair("D", 1)),
        Pair("C", 0) to listOf(Pair("A", 1), Pair("B", 9), Pair("D", 7)),
        Pair("D", 0) to listOf(Pair("A", 3), Pair("B", 1), Pair("C", 1)),
    ).also { TravelingSalesmanProblem(it).apply {
        findPaths()
    } }

    println("\n")

    mapOf (
        Pair("1", 0) to listOf(Pair("2", 10), Pair("4", 20), Pair("3", 15)),
        Pair("2", 0) to listOf(Pair("1", 10), Pair("4", 25), Pair("3", 35)),
        Pair("3", 0) to listOf(Pair("1", 15), Pair("2", 35), Pair("4", 30)),
        Pair("4", 0) to listOf(Pair("1", 20), Pair("2", 25), Pair("3", 30)),
    ).also { TravelingSalesmanProblem(it).apply {
        findPaths()
    } }
}