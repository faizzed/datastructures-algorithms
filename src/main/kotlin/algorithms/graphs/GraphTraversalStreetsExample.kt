package algorithms.graphs

enum class Street(val weight: Int) {
    Rochu(11),
    Stockamp(20),
    Ehren(16),
    Bagel(24),
    Augusten(33),
    Speldorf(21),
    Lennen(19),
    Dusseltal(63),
}

fun main() {
    val vertices = listOf(
        Street.Rochu,
        Street.Stockamp,
        Street.Ehren,
        Street.Bagel,
        Street.Augusten,
        Street.Speldorf,
        Street.Lennen,
        Street.Dusseltal,
    )

    val edges = listOf(
        Pair(Street.Rochu, Street.Stockamp),
        Pair(Street.Rochu, Street.Bagel),
        Pair(Street.Rochu, Street.Ehren),
        Pair(Street.Stockamp, Street.Lennen),
        Pair(Street.Stockamp, Street.Ehren),
        Pair(Street.Ehren, Street.Bagel),
        Pair(Street.Ehren, Street.Lennen),
        Pair(Street.Ehren, Street.Dusseltal),
        Pair(Street.Bagel, Street.Augusten),
        Pair(Street.Bagel, Street.Speldorf),
        Pair(Street.Lennen, Street.Dusseltal),
        Pair(Street.Speldorf, Street.Dusseltal),
        Pair(Street.Lennen, Street.Augusten),
        Pair(Street.Augusten, Street.Speldorf),
        Pair(Street.Augusten, Street.Dusseltal),
    )

    val graph = GraphTraversal<Street>()
    vertices.forEach { graph.addVertex(it) }
    edges.forEach { graph.addEdge(it.first, it.second) }

    println(graph)
    graph.bfs(Street.Rochu, Street.Dusseltal)
    print("\n\n")
    graph.dfs(Street.Rochu, Street.Dusseltal, mutableListOf(), Street.Rochu.weight)
}