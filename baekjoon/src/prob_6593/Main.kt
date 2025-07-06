package prob_6593

import java.util.*
import kotlin.properties.Delegates

var L by Delegates.notNull<Int>()
var R by Delegates.notNull<Int>()
var C by Delegates.notNull<Int>()

lateinit var map: Array<Array<IntArray>>

fun main() {
    val br = System.`in`.bufferedReader()

    val result = mutableListOf<String>()

    while (true) {
        val given = br.readLine()
        if (given == "0 0 0") {
            break
        }

        val nums = given.split(" ").map { it.toInt() }
        L = nums[0]
        R = nums[1]
        C = nums[2]

        // start: 1, empty: 0, gold: -1, end: 2
        map = Array(L) { Array(R) { IntArray(C) } }

        var start = Triple(0, 0, 0)

        for (l in 0 until L) {
            for (r in 0 until R) {
                val input = br.readLine()

                for (c in 0 until C) {
                    val parsed = when (input[c]) {
                        'S' -> {
                            start = Triple(l, r, c)
                            1
                        }
                        '.' -> {
                            0
                        }
                        '#' -> {
                            -1
                        }
                        'E' -> {
                            2
                        }
                        else -> throw IllegalArgumentException()
                    }

                    map[l][r][c] = parsed
                }
            }
            br.readLine()
        }

        val cost = search(start)

        if (cost == Int.MAX_VALUE) {
            result.add("Trapped!")
        } else {
            result.add("Escaped in $cost minute(s).")
        }
    }

    println(result.joinToString("\n"))

    br.close()
}

fun search(start: Triple<Int, Int, Int>): Int {
    val visit = Array(L) { Array(R) { BooleanArray(C) { false } } }
    visit[start.first][start.second][start.third] = true

    val queue: Queue<Step> = LinkedList()
    queue.add(
        Step(
            position = start,
            time = 0,
        )
    )

    var min = Int.MAX_VALUE

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (current.isEnd()) {
            min = minOf(min, current.time)
            continue
        }

        for (i in 0..5) {
            val nextL = current.position.first + dirL[i]
            val nextR = current.position.second + dirR[i]
            val nextC = current.position.third + dirC[i]

            if (isOut(nextL, nextR, nextC)) continue
            if (visit[nextL][nextR][nextC]) continue
            if (map[nextL][nextR][nextC] == -1) continue

            visit[nextL][nextR][nextC] = true

            queue.add(
                Step(
                    position = Triple(nextL, nextR, nextC),
                    time = current.time + 1,
                )
            )
        }
    }

    return min
}

fun isOut(l: Int, r: Int, c: Int)
        = l < 0 || l >= L || r < 0 || r >= R || c < 0 || c >= C

data class Step(
    val position: Triple<Int, Int, Int>,
    val time: Int,
) {
    fun isEnd() = map[position.first][position.second][position.third] == 2
}

val dirL = intArrayOf(0, 0, 0, 0, -1, 1)

val dirR = intArrayOf(-1, 1, 0, 0, 0, 0)

val dirC = intArrayOf(0, 0, -1, 1, 0, 0)
