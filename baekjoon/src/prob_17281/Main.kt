package prob_17281

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
lateinit var innings: List<List<Int>>
var maxScore = 0

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    innings = List(N) {
        br.readLine().split(" ").map { it.toInt() }
    }

    val visited = BooleanArray(9)
    val order = IntArray(9)
    order[3] = 0
    visited[0] = true

    generateBattingOrder(0, visited, order)

    println(maxScore)
    br.close()
}

fun generateBattingOrder(depth: Int, visited: BooleanArray, order: IntArray) {
    if (depth == 9) {
        simulate(order)
        return
    }

    if (depth == 3) {
        generateBattingOrder(depth + 1, visited, order)
        return
    }

    for (i in 1..8) {
        if (!visited[i]) {
            visited[i] = true
            order[depth] = i
            generateBattingOrder(depth + 1, visited, order)
            visited[i] = false
        }
    }
}

fun simulate(order: IntArray) {
    var score = 0
    var current = 0

    for (inning in innings) {
        var outs = 0
        val bases = BooleanArray(3)

        while (outs < 3) {
            val hitter = order[current]
            val result = inning[hitter]

            when (result) {
                0 -> outs++
                1, 2, 3 -> {
                    score += go(bases, result)
                    bases[result - 1] = true
                }
                4 -> {
                    score += go(bases, 4) + 1
                }
            }

            current = (current + 1) % 9
        }
    }

    maxScore = maxOf(maxScore, score)
}

fun go(bases: BooleanArray, hit: Int): Int {
    var score = 0

    for (i in 2 downTo 0) {
        if (bases[i]) {
            val newPos = i + hit
            if (newPos >= 3) {
                score++
            } else {
                bases[newPos] = true
            }
            bases[i] = false
        }
    }

    return score
}