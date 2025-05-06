package prob_2096

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

lateinit var map: Array<IntArray>

var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    map = Array(N) { IntArray(3) }

    for (i in 0 until N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        map[i][0] = given[0]
        map[i][1] = given[1]
        map[i][2] = given[2]
    }

    val dpMax = Array(N + 1) { IntArray(3) }

    for (i in 0 until N) {
        dpMax[i + 1][0] = maxOf(dpMax[i][0], dpMax[i][1]) + map[i][0]
        dpMax[i + 1][1] = maxOf(dpMax[i][0], dpMax[i][1], dpMax[i][2]) + map[i][1]
        dpMax[i + 1][2] = maxOf(dpMax[i][1], dpMax[i][2]) + map[i][2]
    }

    val dpMin = Array(N + 1) { IntArray(3) }

    for (i in 0 until N) {
        dpMin[i + 1][0] = minOf(dpMin[i][0], dpMin[i][1]) + map[i][0]
        dpMin[i + 1][1] = minOf(dpMin[i][0], dpMin[i][1], dpMin[i][2]) + map[i][1]
        dpMin[i + 1][2] = minOf(dpMin[i][1], dpMin[i][2]) + map[i][2]
    }

    val max = maxOf(dpMax[N][0], dpMax[N][1], dpMax[N][2])
    val min = minOf(dpMin[N][0], dpMin[N][1], dpMin[N][2])

    println("$max $min")

    br.close();
}