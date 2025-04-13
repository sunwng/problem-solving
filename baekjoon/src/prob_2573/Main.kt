package prob_2573

import java.util.*
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

lateinit var map: Array<IntArray>

val dirN = arrayOf(-1, 1, 0, 0)
val dirM = arrayOf(0, 0, -1, 1)

fun main() {
    val br = System.`in`.bufferedReader()

    var given = br.readLine().split(" ").map { it.toInt() }
    N = given[0]
    M = given[1]

    map = Array(N) { IntArray(M) }

    for (i in 0 until N) {
        given = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until M) {
            map[i][j] = given[j]
        }
    }

    var year = 0
    while (true) {
        year++
        melt()
        if (checkNumberIsOverTwo()) break
        if (checkAllMelt()) {
            year = 0
            break
        }

    }

    println(year)

    br.close();
}

fun checkAllMelt(): Boolean {
    for (n in 0 until N) {
        for (m in 0 until M) {
            if (map[n][m] > 0) return false
        }
    }
    return true
}

fun checkNumberIsOverTwo(): Boolean {
    val visit = Array(N) { BooleanArray(M) { false } }

    var count = 0
    for (n in 0 until N) {
        for (m in 0 until M) {
            if (map[n][m] == 0) {
                visit[n][m] = true
                continue
            }
            if (visit[n][m]) continue
            count++
            val queue: Queue<Pair<Int, Int>> = LinkedList()
            queue.add(Pair(n, m))

            while (queue.isNotEmpty()) {
                val current = queue.poll()

                for (i in 0..3) {
                    val targetN = current.first + dirN[i]
                    val targetM = current.second + dirM[i]
                    if (checkOut(targetN, targetM)) continue
                    if (map[targetN][targetM] == 0) continue
                    if (visit[targetN][targetM]) continue

                    visit[targetN][targetM] = true
                    queue.add(Pair(targetN, targetM))
                }
            }

            if (count > 1) return true
        }
    }

    return false
}

fun melt() {
    val newMap = Array(N) { IntArray(M) }

    for (n in 0 until N) {
        for (m in 0 until M) {
            newMap[n][m] = maxOf(map[n][m] - countSurface(n, m), 0)
        }
    }

    map = newMap
}

fun countSurface(n: Int, m: Int): Int {
    var count = 0

    for (i in 0..3) {
        val targetN = n + dirN[i]
        val targetM = m + dirM[i]
        if (checkOut(targetN, targetM)) continue
        if (map[targetN][targetM] == 0) {
            count++
        }
    }
    return count
}

fun checkOut(n: Int, m: Int): Boolean {
    return n < 0 || n >= N || m < 0 || m >= M
}