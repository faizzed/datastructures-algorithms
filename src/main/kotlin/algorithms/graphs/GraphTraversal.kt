package algorithms.graphs

import java.util.*

class GraphTraversal<T> {
    val graph = mutableMapOf<T, LinkedList<T>>()

    fun addVertex(airport: T) {
        graph[airport] = LinkedList()
    }

    fun addEdge(origin: T, destination: T) {
        graph[origin]?.push(destination)
        graph[destination]?.push(origin)
    }

    // this simple method will fail to say that there is a route
    // between frankfurt to hanover
    // frankfurt to hanover route exists through duss..
    // thats why now we need the the DFS or tree search algorithm
    fun routeExists(origin: T, destination: T): Boolean {
        if (!graph.containsKey(origin)) {
            return false
        }

        return graph[origin]?.contains(destination)!!
    }

    /**
     * Visit all vertices starting from a start point -- Berlin lets say
     * Every vertices has a linked list and children in that linked list in itself act as vertices of its own
     * visit all of them to find all the routes that connects the airports...
    */
    fun bfs(origin: T, destination: T) {
        val queue = LinkedList<T>()
        val visited = mutableListOf<T>()
        visited.add(origin)
        queue.add(origin)

        while (!queue.isEmpty()) {
            val vertex = queue.remove()
            if (!visited.contains(vertex)) {
                visited.add(vertex)
            }
            print("\n\nGoing through $vertex:\n")
            for (connectingVertex in graph[vertex]!!) {
                if (connectingVertex == destination) {
                    print("$origin is connected to $destination through $vertex\n")
                } else {
                    print("$connectingVertex....\n")
                }

                if (!visited.contains(connectingVertex)) {
                    if (!queue.contains(connectingVertex)) {
                        queue.add(connectingVertex)
                    }
                }

            }
        }
    }

    fun dfs(origin: T, destination: T, visited: MutableList<T> = mutableListOf(), distance: Int) {
        visited.add(origin)

        for (vertex in graph[origin]!!) {
            if (vertex == destination) {
                print("Connected to $destination through $origin [distance: $distance km]\n")
                return
            } else {
                print("$vertex....\n")
            }

            if (!visited.contains(vertex)) {
                dfs(vertex, destination, visited, distance + (vertex as Street).weight)
            }
        }
    }

    override fun toString(): String {
        return graph.toString()
    }
}