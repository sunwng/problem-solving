package prob_7569

import java.util.*
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
var H by Delegates.notNull<Int>()
lateinit var map: Array<Array<IntArray>>

fun main() {
    val br = System.`in`.bufferedReader()
    val numbers = br.readLine().split(" ").map { it.toInt() }
    M = numbers[0]
    N = numbers[1]
    H = numbers[2]

    map = Array(H) { Array(N) { IntArray(M) } }

    val queue: Queue<Position> = LinkedList()

    for (h in 0 until H) {
        for (n in 0 until N) {
            val given = br.readLine().split(" ").map { it.toInt() }
            for (m in 0 until M) {
                map[h][n][m] = given[m]
                if (given[m] == 1) {
                    queue.add(Position(h, n, m, 0))
                }
            }
        }
    }

    var days = 0

    val dh = arrayOf(0, 0, 0, 0, -1, 1)
    val dn = arrayOf(1, -1, 0, 0, 0, 0)
    val dm = arrayOf(0, 0, 1, -1, 0, 0)

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        days = maxOf(current.day, days)
        for (dir in 0..5) {
            val nextH = current.h + dh[dir]
            val nextN = current.n + dn[dir]
            val nextM = current.m + dm[dir]

            if (isOut(nextH, nextN, nextM)) continue
            if (isPossible(nextH, nextN, nextM).not()) continue
            map[nextH][nextN][nextM] = 1
            queue.add(Position(nextH, nextN, nextM, current.day + 1))
        }
    }

    if (check()) {
        println(days)
    } else {
        println(-1)
    }

    br.close();
}

fun isOut(h: Int, n: Int, m: Int): Boolean
        = h < 0 || h >= H || n < 0 || n >= N || m < 0 || m >= M

fun isPossible(h: Int, n: Int, m: Int) = map[h][n][m] == 0

fun check(): Boolean {
    for (h in 0 until H) {
        for (n in 0 until N) {
            for (m in 0 until M) {
                if (map[h][n][m] == 0) return false
            }
        }
    }
    return true
}

data class Position(
    val h: Int,
    val n: Int,
    val m: Int,
    val day: Int,
)