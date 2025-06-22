package prob_11559

import java.util.*

val M = 12
val N = 6

fun main() {
    // R 1 G 2 B 3 P 4 Y 5
    val br = System.`in`.bufferedReader()

    val map = Array(M) { IntArray(N) }

    for (m in 0 until M) {
        val given = br.readLine()

        for (idx in given.indices) {
            when (given[idx]) {
                'R' -> {
                    map[m][idx] = 1
                }

                'G' -> {
                    map[m][idx] = 2
                }

                'B' -> {
                    map[m][idx] = 3
                }

                'P' -> {
                    map[m][idx] = 4
                }

                'Y' -> {
                    map[m][idx] = 5
                }
            }
        }
    }

    var result = 0

    while (true) {
        val visit = Array(M) { BooleanArray(N) { false } }

        var deleted = false

        for (m in 0 until M) {
            for (n in 0 until N) {
                if (map[m][n] == 0) continue
                if (visit[m][n]) continue
                visit[m][n] = true
                if (find(m, n, map, visit)) {
                    deleted = true
                }
            }
        }

        if (deleted) {
            goingDown(map)
            result++
        } else {
            break
        }
    }

    println(result)

    br.close()
}

fun find(curM: Int, curN: Int, map: Array<IntArray>, visit: Array<BooleanArray>): Boolean {
    val color = map[curM][curN]

    val targets = mutableSetOf<Pair<Int, Int>>()
    targets.add(curM to curN)

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(curM to curN)

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (i in 0..3) {
            val nextM = current.first + dirM[i]
            val nextN = current.second + dirN[i]

            if (isOut(nextM, nextN)) continue
            if (map[nextM][nextN] != color) continue
            if (visit[nextM][nextN]) continue

            visit[nextM][nextN] = true
            targets.add(nextM to nextN)
            queue.add(nextM to nextN)
        }
    }

    if (targets.size >= 4) {
        for (target in targets) {
            map[target.first][target.second] = 0
        }
        return true
    }
    return false
}

fun goingDown(map: Array<IntArray>) {
    for (n in 0 until N) {
        val queue: Queue<Int> = LinkedList()
        for (m in M - 1 downTo 0) {
            if (map[m][n] != 0) {
                queue.add(map[m][n])
                map[m][n] = 0
            }
        }

        var start = M - 1
        while(queue.isNotEmpty()) {
            val current = queue.poll()
            map[start][n] = current
            start--
        }
    }
}

fun isOut(m: Int, n: Int) = m < 0 || m >= M || n < 0 || n >= N

val dirM = arrayOf(-1, 1, 0, 0)
val dirN = arrayOf(0, 0, -1, 1)