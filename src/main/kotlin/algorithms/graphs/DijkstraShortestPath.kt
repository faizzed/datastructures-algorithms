package algorithms.graphs

data class Vertex(val name: String, var distance: Int = Int.MAX_VALUE, var visited: Boolean = false)
{
    val linkedEdges = mutableListOf<Path>()

    fun addEdges(edges: List<Path>) {
        edges.sortedBy { it.weight }.forEach {
            linkedEdges.add(it)
        }
    }

    override fun toString(): String {
        return """
            $name: {$distance} $linkedEdges
        """.trimIndent()
    }
}

data class Path(val start: Vertex, val end: Vertex, val weight: Int) {
    override fun toString(): String {
        return """
            ${start.name} <- $weight -> ${end.name}
        """.trimIndent()
    }
}

data class DistanceRow(val to: String, val distance: Int, val via: String, ) {
    override fun toString(): String {
        return """
             $to | $distance | $via
        """.trimIndent()
    }
}

/**
 * Dijkstra finds the shortest paths inside a graph that connects node x to all the nodes in a graph
 * Why is this important?
 *
 * Nodes inside graph can be connected via multiple paths each carrying its own weight, this algorithm
 * sift through all the connected paths and find the shortest paths that a node x can connect to its neighbours
 *
 * But kruskal MST does the same thing, it orders the paths via desc order and find the shortest paths that doesnt make a cycle
 * Diff being, dijkstra algorithm updates the path along the way, it adjust the distance via the shortest visited route
 *
 * if we visited A --4-- B --9-- Z
 * and also     A --1-- C --9-- Z
 *
 * then distance to Z via B is 13 and via C is 10, we only consider the path via C because its the shorted one
 * we also add this information to the vertex.
 *
 * The data structure to make this algorithm work is also important
 * vertex must have its connected edges and its distance
 * edges must carry the weight thats how we can identify the
 *
 * More in this video https://www.youtube.com/watch?v=pVfj6mxhdMw&t=561s&ab_channel=ComputerScience
* */
class Dijkstra {
    val distances = mutableListOf<DistanceRow>()

    fun shortestPath(vertex: Vertex, d: Int = 0) {
        vertex.visited = true

        for (edge in vertex.linkedEdges) {
            val distance = d + edge.weight
            val connectedEdge = edge.end

            if (distance < connectedEdge.distance) {
                connectedEdge.distance = distance
                distances.add(DistanceRow(connectedEdge.name, distance, vertex.name))
            }
            if (!connectedEdge.visited) {
                shortestPath(connectedEdge, connectedEdge.distance)
            }
        }
    }
}

fun main() {

    val A = Vertex("A")
    val B = Vertex("B")
    val C = Vertex("C")
    val D = Vertex("D")
    val E = Vertex("E")

    A.addEdges(listOf(
        Path(A, B, 6),
        Path(A, D, 1),
    ))

    B.addEdges(listOf(
        Path(B, C, 5),
        Path(B, E, 2),
    ))

    D.addEdges(listOf(
        Path(D, B, 2),
        Path(D, E, 1),
    ))

    E.addEdges(listOf(
        Path(E, C, 5),
    ))

    Dijkstra().apply {
        shortestPath(A)
        print("Distances:\n---------------\nTo|Dist|Via\n---------------\n")
        distances.forEach {
            println(it)
        }
    }

    val zero = Vertex("0")
    val one = Vertex("1")
    val two = Vertex("2")
    val three = Vertex("3")
    val four = Vertex("4")

    zero.addEdges(listOf(
        Path(zero, one, 3),
        Path(zero, four, 8),
        Path(zero, three, 7),
    ))

    one.addEdges(listOf(
        Path(one, two, 1),
        Path(one, three, 4),
    ))

    two.addEdges(listOf(
        Path(two, three, 2),
    ))

    three.addEdges(listOf(
        Path(three, two, 2),
    ))

    four.addEdges(listOf(
        Path(four, three, 3)
    ))

    Dijkstra().apply {
        shortestPath(zero)
        print("Distances:\n---------------\nTo|Dist|Via\n---------------\n")
        distances.forEach {
            println(it)
        }
    }

}