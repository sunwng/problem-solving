package prob_2660

import java.util.PriorityQueue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var relations: MutableList<MutableList<Int>>

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    relations = mutableListOf()

    repeat(N + 1) {
        relations.add(mutableListOf())
    }

    while (true) {
        val given = br.readLine().split(" ").map { it.toInt() }
        if (given[0] == -1) {
            break
        }
        relations[given[0]].add(given[1])
        relations[given[1]].add(given[0])
    }

    val result = IntArray(N + 1)
    for (i in 1..N) {
        result[i] = find(start = i)
    }

    val min = result.filter { it != 0 }.min()
    val candidates = mutableListOf<Int>()
    for (i in 1..N) {
        if (result[i] == min) {
            candidates.add(i)
        }
    }

    println("$min ${candidates.size}")
    println(candidates.joinToString(" "))

    br.close()
}

fun find(start: Int): Int {
    val distance = IntArray(N + 1) { Int.MAX_VALUE }
    distance[0] = 0
    distance[start] = 0
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.add(start to 0)

    while (pq.isNotEmpty()) {
        val current = pq.poll()

        if (current.second != distance[current.first]) continue

        for (friend in relations[current.first]) {
            if (current.second + 1 < distance[friend]) {
                distance[friend] = current.second + 1
                pq.add(friend to current.second + 1)
            }
        }
    }

    return distance.filter { it != 0 }.max()
}