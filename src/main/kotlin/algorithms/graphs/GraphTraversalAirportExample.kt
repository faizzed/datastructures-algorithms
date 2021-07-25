package algorithms.graphs

enum class Airports(val weight: Int) {
    Dusseldorf(33),
    Hanover(20),
    Berlin(16),
    Frankfurt(24),
    Hamburg(33),
    Heidelberg(33),
    Nuremberg(33),
    Dresden(33),
    Prague(33),
    Rostock(33),
}

fun main() {
    val vertices = listOf(
        Airports.Dusseldorf,
        Airports.Hanover,
        Airports.Berlin,
        Airports.Frankfurt,
        Airports.Hamburg,
        Airports.Heidelberg,
        Airports.Nuremberg,
        Airports.Dresden,
        Airports.Prague,
        Airports.Rostock,
    )

    val edges = listOf(
        Pair(Airports.Dusseldorf, Airports.Hanover),
        Pair(Airports.Dusseldorf, Airports.Frankfurt),
        Pair(Airports.Hanover, Airports.Berlin),
        Pair(Airports.Berlin, Airports.Dresden),
        Pair(Airports.Frankfurt, Airports.Dresden),
        Pair(Airports.Frankfurt, Airports.Nuremberg),
        Pair(Airports.Frankfurt, Airports.Heidelberg),
        Pair(Airports.Nuremberg, Airports.Dresden),
        Pair(Airports.Nuremberg, Airports.Prague),
        Pair(Airports.Dresden, Airports.Prague),
        Pair(Airports.Berlin, Airports.Rostock),
        Pair(Airports.Hanover, Airports.Rostock),
    )

    val graph = GraphTraversal<Airports>()
    vertices.forEach { graph.addVertex(it) }
    edges.forEach { graph.addEdge(it.first, it.second) }

    println(graph)
    graph.bfs(Airports.Dusseldorf, Airports.Rostock)
    print("\n\n")
    graph.dfs(Airports.Dusseldorf, Airports.Rostock, mutableListOf(), Airports.Dusseldorf.weight)
}