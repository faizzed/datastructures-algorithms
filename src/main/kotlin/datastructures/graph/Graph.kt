/**
 * Concept:
 *
 * A graph represents relation/connectivity/model of objects in a particular space.
 * such as
 * computers connected across internet
 * houses connected via streets
 * human ancestry
 *
 *
 * A graph is a set of vertices and edges
 * G = (V, E)
 *
 * A vertex is an actual object like a city..
 * An edge is the connection if city a connects to another city in this set.
 *
 * In a undirected graph, just the connectivity is represented. So we will do just that.
 *
 * Implementation:
 *
 * Since we need connections between vertices, we can use an array of linked lists
 * */

package datastructures.graph

import java.util.*

enum class Vertices {
    BERLIN, MUNICH, HAMBURG, COLOGNE, FRANKFURT
}

data class City(val name: Vertices) {
    override fun toString(): String {
        return name.toString()
    }
}

open class Graph {
    private val graph = mutableMapOf<Vertices, LinkedList<City>>()

    fun add(label: Vertices, vararg city: City) {
        city.forEach {
            if (graph[label] == null) {
                val l = LinkedList<City>()
                l.add(it)
                graph.put(label, l)
            } else {
                graph[label]?.add(it)
            }
        }
    }

    fun removeVertex(label: Vertices) {
        if (graph[label] !== null) {
            graph.remove(label)
        }
    }

    fun removeEdge(label: Vertices, edge: Vertices) {
        if (graph[label] !== null) {
            graph[label]?.remove(City(edge))
        }
    }

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
    open fun depthFirstTraversal(root: Vertices): MutableList<Vertices> {
        val visited = mutableListOf<Vertices>()
        val traversalStack = Stack<Vertices>()
        traversalStack.push(root)

        while (traversalStack.isNotEmpty()) {
            val vertex = traversalStack.pop()
            visited.add(vertex)

            graph[vertex]?.forEach {
                if (!visited.contains(it.name) && !traversalStack.contains(it.name)) {
                    traversalStack.push(it.name)
                }
            }
        }

        return visited
    }

    /**
     * Breadth first search:
     * I hate this name, anyway
     *
     * It will explore the nodes at each branch like level by level rather then going to the edge on a single branch
     *
     * suppose
     *
     *          b - m - f
     *          |  / /
     *          h - c
     *
     * If we start at root b -> it will go through m -> h -> f -> c
     *
    * */
    fun breadthFirstSearch(root: Vertices): MutableList<Vertices> {
        val visited = mutableListOf<Vertices>()
        val traversalQueue = LinkedList<Vertices>()
        traversalQueue.push(root)
        visited.add(root)

        while (traversalQueue.isNotEmpty()) {
            val vertex = traversalQueue.poll()

            graph[vertex]?.forEach {
                if (!visited.contains(it.name) && !traversalQueue.contains(it.name)) {
                    traversalQueue.push(it.name)
                    visited.add(it.name)
                }
            }
        }
        return visited
    }

    override fun toString(): String {
        graph.forEach { k, v ->
            print("$k: ")
            v.forEach {
                print("${it.name} -> ")
            }
            print("\n")
        }
        return graph.toString()
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
    Graph().apply {
        add(Vertices.BERLIN, City(Vertices.MUNICH), City(Vertices.HAMBURG))
        add(Vertices.MUNICH, City(Vertices.BERLIN), City(Vertices.FRANKFURT), City(Vertices.HAMBURG), City(Vertices.COLOGNE))
        add(Vertices.HAMBURG, City(Vertices.BERLIN), City(Vertices.MUNICH), City(Vertices.COLOGNE))
        add(Vertices.COLOGNE, City(Vertices.FRANKFURT), City(Vertices.MUNICH), City(Vertices.HAMBURG))
        add(Vertices.FRANKFURT, City(Vertices.MUNICH), City(Vertices.COLOGNE))

//        removeVertex(Vertices.BERLIN)
//        removeEdge(Vertices.HAMBURG, Vertices.COLOGNE)
        depthFirstTraversal(Vertices.BERLIN).also(::println)
        breadthFirstSearch(Vertices.BERLIN).also(::println)
    }
}