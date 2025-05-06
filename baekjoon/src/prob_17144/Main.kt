package prob_17144

import kotlin.properties.Delegates

var R by Delegates.notNull<Int>()
var C by Delegates.notNull<Int>()
var T by Delegates.notNull<Int>()

var UP by Delegates.notNull<Int>()
var DOWN by Delegates.notNull<Int>()

lateinit var map: Array<IntArray>

var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(" ").map { it.toInt() }.let {
        R = it[0]
        C = it[1]
        T = it[2]
    }

    map = Array(R) { IntArray(C) }

    var check = false

    for (i in 0 until R) {
        val given = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until C) {
            map[i][j] = given[j]

            if (map[i][j] == -1) {
                if (check.not()) {
                    UP = i
                    check = true
                } else {
                    DOWN = i
                }
            }
        }
    }

    repeat(T) {
        spread()
        rotate()
    }

    var result = 0
    for (r in 0 until R) {
        for (c in 0 until C) {
            if (map[r][c] == 0 || map[r][c] == -1) continue
            result += map[r][c]
        }
    }

    println(result)

    br.close();
}

fun rotate() {
    val new = Array(R) { IntArray(C) }
    new[UP][0] = -1
    new[DOWN][0] = -1

    for (c in 0 until C - 1) {
        new[0][c] = map[0][c + 1]
        new[R - 1][c] = map[R - 1][c + 1]
    }
    for (c in 2 until C) {
        new[UP][c] = map[UP][c - 1]
        new[DOWN][c] = map[DOWN][c - 1]
    }

    for (r in 1 until UP) {
        new[r][0] = map[r - 1][0]
    }
    for (r in 0 until UP) {
        new[r][C - 1] = map[r + 1][C - 1]
    }

    for (r in DOWN + 1 until R - 1) {
        new[r][0] = map[r + 1][0]
    }
    for (r in DOWN + 1 until R) {
        new[r][C - 1] = map[r - 1][C - 1]
    }

    for (r in 1 until R - 1) {
        for (c in 1 until C - 1) {
            if (r == UP || r == DOWN) continue
            new[r][c] = map[r][c]
        }
    }

    map = new
}

fun spread() {
    val new = Array(R) { IntArray(C) }
    new[UP][0] = -1
    new[DOWN][0] = -1

    val dirR = arrayOf(-1, 1, 0, 0)
    val dirC = arrayOf(0, 0, -1, 1)

    for (r in 0 until R) {
        for (c in 0 until C) {
            if (map[r][c] == 0 || map[r][c] == -1) continue

            var count = 0
            val amount = map[r][c] / 5

            for (dir in 0..3) {
                val nextR = r + dirR[dir]
                val nextC = c + dirC[dir]

                if (isOut(nextR, nextC)) continue
                if (isCleaner(nextR, nextC)) continue
                count++
                new[nextR][nextC] += amount
            }

            new[r][c] += (map[r][c] - amount * count)
        }
    }

    map = new
}

fun isOut(r: Int, c: Int) = r < 0 || r >= R || c < 0 || c >= C

fun isCleaner(r: Int, c: Int) = map[r][c] == -1