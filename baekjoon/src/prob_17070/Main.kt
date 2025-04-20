package prob_17070

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>
var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    map = Array(N) { IntArray(N) }

    for (i in 0 until N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until N) {
            map[i][j] = given[j]
        }
    }

    result = 0

    find(
        Pipe(
            start = Pair(0, 0),
            end = Pair(0, 1),
            direction = -1
        )
    )

    println(result)

    br.close();
}

fun find(current: Pipe) {
    if (current.isDestination()) {
        result++
        return
    }

    when(current.direction) {
        // 가로
        -1 -> {
            val horizon = addHorizon(current)
            if (horizon != null) {
                find(horizon)
            }

            val diagonal = addDiagonal(current)
            if (diagonal != null) {
                find(diagonal)
            }
        }

        // 세로
        1 -> {
            val vertical = addVertical(current)
            if (vertical != null) {
                find(vertical)
            }

            val diagonal = addDiagonal(current)
            if (diagonal != null) {
                find(diagonal)
            }
        }

        // 대각
        0 -> {
            val horizon = addHorizon(current)
            if (horizon != null) {
                find(horizon)
            }

            val vertical = addVertical(current)
            if (vertical != null) {
                find(vertical)
            }

            val diagonal = addDiagonal(current)
            if (diagonal != null) {
                find(diagonal)
            }
        }
    }
}

fun addHorizon(current: Pipe): Pipe? {
    val targetN = current.end.first
    val targetM = current.end.second + 1
    if (isOut(targetN, targetM).not() && map[targetN][targetM] == 0) {
        return Pipe(
            start = Pair(current.end.first, current.end.second),
            end = Pair(targetN, targetM),
            direction = -1,
        )
    } else return null
}

fun addVertical(current: Pipe): Pipe? {
    val targetN = current.end.first + 1
    val targetM = current.end.second
    if (isOut(targetN, targetM).not() && map[targetN][targetM] == 0) {
        return Pipe(
            start = Pair(current.end.first, current.end.second),
            end = Pair(targetN, targetM),
            direction = 1,
        )
    } else return null
}

fun addDiagonal(current: Pipe): Pipe? {
    val targetN = current.end.first + 1
    val targetM = current.end.second + 1
    if (isOut(targetN, targetM).not() && map[targetN][targetM] == 0 && checkDiagonal(targetN, targetM)) {
        return Pipe(
            start = Pair(current.end.first, current.end.second),
            end = Pair(targetN, targetM),
            direction = 0,
        )
    } else return null
}

fun checkDiagonal(n: Int, m: Int) = map[n - 1][m] == 0 && map[n][m - 1] == 0

fun isOut(n: Int, m: Int) = n < 0 || n >= N || m < 0 || m >= N

data class Pipe(
    val start: Pair<Int, Int>,
    val end: Pair<Int, Int>,
    val direction: Int,
) {
    fun isDestination(): Boolean {
        return this.end.first == N - 1 && this.end.second == N - 1
    }
}
