package prob_1504

import java.util.*
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

lateinit var map: Array<IntArray>

var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    var given = br.readLine().split(" ").map { it.toInt() }
    N = given[0]

    map = Array(N + 1) { IntArray(N + 1) }

    val E = given[1]
    repeat(E) {
        val edge = br.readLine().split(" ").map { it.toInt() }
        map[edge[0]][edge[1]] = edge[2]
        map[edge[1]][edge[0]] = edge[2]
    }

    given = br.readLine().split(" ").map { it.toInt() }

    val mustFirst = given[0]
    val mustSecond = given[1]

    var result = Int.MAX_VALUE

    val fromOneToMustFirst = find(1, mustFirst)
    val fromFirstToSecond = find(mustFirst, mustSecond)
    val fromSecondToN = find(mustSecond, N)

    if (fromOneToMustFirst != Int.MAX_VALUE && fromFirstToSecond != Int.MAX_VALUE && fromSecondToN != Int.MAX_VALUE) {
        result = minOf(result, fromOneToMustFirst + fromFirstToSecond + fromSecondToN)
    }

    val fromOneToMustSecond = find(1, mustSecond)
    val fromSecondToFirst = find(mustSecond, mustFirst)
    val fromFirstToN = find(mustFirst, N)

    if (fromOneToMustSecond != Int.MAX_VALUE && fromSecondToFirst != Int.MAX_VALUE && fromFirstToN != Int.MAX_VALUE) {
        result = minOf(result, fromOneToMustSecond + fromSecondToFirst + fromFirstToN)
    }

    if (result == Int.MAX_VALUE) {
        result = -1
    }

    println(result)

    br.close();
}

fun find(
    start: Int,
    end: Int
): Int {
    val distances = IntArray(N + 1) { Int.MAX_VALUE }
    distances[start] = 0

    val pq = PriorityQueue(compareBy<Pair<Int, Int>> { it.second })
    pq.add(Pair(start, 0))

    while (pq.isNotEmpty()) {
        val current = pq.poll()

        if (current.second > distances[current.first]) continue

        for (i in 1 .. N) {
            if (map[current.first][i] == 0) continue

            val added = current.second + map[current.first][i]

            if (distances[i] <= added) continue
            distances[i] = added
            pq.add(Pair(i, added))
        }
    }

    return distances[end]
}