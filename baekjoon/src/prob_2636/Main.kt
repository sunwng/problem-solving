package prob_2636

import java.util.*

var R = 0
var C = 0

lateinit var map: Array<IntArray>
lateinit var holeMap: Array<BooleanArray>
lateinit var visit: Array<BooleanArray>

val dirR = arrayOf(-1, 1, 0, 0)
val dirC = arrayOf(0, 0, -1, 1)

fun main() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(" ").let {
        R = it[0].toInt()
        C = it[1].toInt()
    }

    map = Array(R) { IntArray(C) }
    holeMap = Array(R) { BooleanArray(C) }
    visit = Array(R) { BooleanArray(C) }

    for (r in 0 until R) {
        val given = br.readLine().split(" ").map { it.toInt() }
        for (c in 0 until C) {
            map[r][c] = given[c]
        }
    }

    var time = 0
    var lastCount = 0

    while (true) {
        val currentCount = countCheese()
        if (currentCount == 0) {
            break
        }

        lastCount = currentCount
        time++

        findHoles()
        melt()
    }

    println(time)
    println(lastCount)
    br.close()
}

fun findHoles() {
    for (r in 0 until R) {
        visit[r].fill(false)
        holeMap[r].fill(false)
    }

    val queue: Queue<Pair<Int, Int>> = LinkedList()

    for (r in 0 until R) {
        for (c in 0 until C) {
            if (visit[r][c] || map[r][c] != 0) continue

            var isHole = true
            val candidates = mutableListOf<Pair<Int, Int>>()
            queue.clear()
            queue.add(r to c)
            visit[r][c] = true

            while (queue.isNotEmpty()) {
                val (currentR, currentC) = queue.poll()
                candidates.add(currentR to currentC)

                if (isEnd(currentR, currentC)) {
                    isHole = false
                }

                for (i in 0..3) {
                    val nr = currentR + dirR[i]
                    val nc = currentC + dirC[i]
                    if (isOut(nr, nc)) continue
                    if (map[nr][nc] != 0 || visit[nr][nc]) continue
                    visit[nr][nc] = true
                    queue.add(nr to nc)
                }
            }

            if (isHole) {
                for ((hr, hc) in candidates) {
                    holeMap[hr][hc] = true
                }
            }
        }
    }
}

fun melt() {
    val toRemove = mutableListOf<Pair<Int, Int>>()

    for (r in 0 until R) {
        for (c in 0 until C) {
            if (map[r][c] != 1) continue

            for (i in 0..3) {
                val nextR = r + dirR[i]
                val nextC = c + dirC[i]
                if (isOut(nextR, nextC)) continue
                if (map[nextR][nextC] == 0 && !holeMap[nextR][nextC]) {
                    toRemove.add(r to c)
                    break
                }
            }
        }
    }

    for ((r, c) in toRemove) {
        map[r][c] = 0
    }
}

fun countCheese(): Int {
    var count = 0
    for (r in 0 until R) {
        for (c in 0 until C) {
            if (map[r][c] == 1) count++
        }
    }
    return count
}

fun isEnd(r: Int, c: Int) = r == 0 || r == R - 1 || c == 0 || c == C - 1

fun isOut(r: Int, c: Int) = r !in 0 until R || c !in 0 until C