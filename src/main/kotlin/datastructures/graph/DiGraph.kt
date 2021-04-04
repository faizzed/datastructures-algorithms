package datastructures.graph

import java.util.*

/**
 * DiGraph is a directional graph where each edge has an in and out and the edge has also associated cost/weight with it
 * If we connect cities, the weight/cost will be the distance between them, for example.
 *
 * The final graph will look like this
 * G = (V, E)
 * G = ({berlin, munich, hamburg...}, {berlin, munich}, {berlin, hamburg}, ...)
* */

data class Edge(val src: Vertices, val dest: Vertices, val weight: Int)

class DiGraph: Graph() {
    // we will use adjacency list to build up digraph
    private val diGraph = mutableMapOf<Vertices, LinkedList<Edge>>()

    fun add(vararg edge: Edge) {
        edge.forEach {
            if (diGraph[it.src] == null) {
                val l = LinkedList<Edge>()
                l.add(it)
                diGraph.put(it.src, l)
            } else {
                diGraph[it.src]?.add(it)
            }
        }
    }

    /*
    * Remove edge can be done the same way..
    *
    * Find the src from map -- look for the objects with src and edge and remove it..
    * */
    fun removeEdge() {}

    /**
     * Remove vertex..
     * remove the map key..
    * */
    fun removeVertex() {}

    /**
     * Depth first search:
     *
     * It goes to the edge of each branch.
     *
     * suppose
     *
     *          b - m - f
     *          |  / /
     *          h - c
     *
     * If we start at root b -> it will go through h -> c -> f -> m
     * */
    override fun depthFirstTraversal(root: Vertices): MutableList<Vertices> {
        val visited = mutableListOf<Vertices>()
        val traversalStack = Stack<Vertices>()
        traversalStack.push(root)

        while (traversalStack.isNotEmpty()) {
            val vertex = traversalStack.pop()
            visited.add(vertex)

            diGraph[vertex]?.forEach {
                if (!visited.contains(it.dest) && !traversalStack.contains(it.dest)) {
                    traversalStack.push(it.dest)
                }
            }
        }

        return visited
    }

    override fun toString(): String {
        return diGraph.toString()
    }
}

/**
 * We will add cities such as
 *
 *            (Berlin)----(Munich)---(Frankfurt)
 *            |           /   |       /
 *            |         /     |      /
 *            |       /       |     /
 *            |     /         |    /
 *         (Hamburg) ------(Cologne)
 *
 * */
fun main() {
    DiGraph().apply {
        add(
            Edge(Vertices.BERLIN, Vertices.MUNICH, 2),
            Edge(Vertices.BERLIN, Vertices.HAMBURG, 3),
            Edge(Vertices.MUNICH, Vertices.HAMBURG, 3),
            Edge(Vertices.MUNICH, Vertices.BERLIN, 3),
            Edge(Vertices.MUNICH, Vertices.COLOGNE, 3),
            Edge(Vertices.MUNICH, Vertices.FRANKFURT, 3),
            Edge(Vertices.HAMBURG, Vertices.BERLIN, 3),
            Edge(Vertices.HAMBURG, Vertices.MUNICH, 3),
            Edge(Vertices.HAMBURG, Vertices.COLOGNE, 3),
            Edge(Vertices.COLOGNE, Vertices.HAMBURG, 3),
            Edge(Vertices.COLOGNE, Vertices.MUNICH, 3),
            Edge(Vertices.COLOGNE, Vertices.FRANKFURT, 3),
            Edge(Vertices.FRANKFURT, Vertices.MUNICH, 3),
            Edge(Vertices.FRANKFURT, Vertices.COLOGNE, 3),
        )
        also(::println)
        depthFirstTraversal(Vertices.BERLIN).also(::println)
    }
}