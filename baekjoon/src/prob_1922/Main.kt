package prob_1922

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

lateinit var parent: IntArray


fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()
    M = br.readLine().toInt()

    parent = IntArray(N + 1) { it }

    val edges = mutableListOf<Edge>()

    repeat(M) {
        val given = br.readLine().split(" ").map { it.toInt() }
        edges.add(
            Edge(
                first = given[0],
                second = given[1],
                cost = given[2],
            )
        )
    }
    edges.sortBy { it.cost }

    var result = 0

    for (edge in edges) {
        val firstParent = findParent(edge.first)
        val secondParent = findParent(edge.second)

        if (firstParent == secondParent) continue

        if (firstParent < secondParent) {
            parent[secondParent] = firstParent
        } else {
            parent[firstParent] = secondParent
        }

        result += edge.cost
    }

    println(result)

    br.close();
}

fun findParent(current: Int): Int {
    if (parent[current] == current) {
        return current
    }
    return findParent(parent[current])
}

data class Edge(
    val first: Int,
    val second: Int,
    val cost: Int,
)