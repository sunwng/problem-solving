package prob_11404

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    M = br.readLine().toInt()

    map = Array(N) { IntArray(N) { Int.MAX_VALUE } }

    repeat(M) {
        val given = br.readLine().split(" ").map { it.toInt() }
        map[given[0] - 1][given[1] - 1] = minOf(given[2], map[given[0] - 1][given[1] - 1])
    }

    for (k in 0 until N) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (i == j) {
                    map[i][j] = 0
                    continue
                }
                if (map[i][k] == Int.MAX_VALUE || map[k][j] == Int.MAX_VALUE) continue
                map[i][j] = minOf(map[i][j], map[i][k] + map[k][j])
            }
        }
    }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (map[i][j] == Int.MAX_VALUE) {
                map[i][j] = 0
            }
        }
    }

    map.forEach { println(it.joinToString(" ")) }

    br.close();
}