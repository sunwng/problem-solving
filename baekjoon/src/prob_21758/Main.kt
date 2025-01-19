package prob_21758

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    val sequence = br.readLine().split(' ').map { it.toInt() }

    val fromLeft = LongArray(N + 2)
    val fromRight = LongArray(N + 2)

    var max = Long.MIN_VALUE

    for (i in 1 until N) {
        fromLeft[i] = fromLeft[i - 1] + sequence[i - 1]
        fromRight[N + 1 - i] = fromRight[N + 2 - i] + sequence[N - i]
    }

    for (i in 2 until N) {
        val leftFixed = fromLeft[N - 1] + fromLeft[i - 1] - sequence[i - 1]
        val rightFixed = fromRight[2] + fromRight[i + 1] - sequence[i - 1]
        val middle = fromLeft[i] - sequence[0] + fromRight[i] - sequence[N - 1]
        max = maxOf(leftFixed, rightFixed, middle, max)
    }

    println(max)

    br.close();
}