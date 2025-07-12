package prob_1091

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

lateinit var target: IntArray
lateinit var sequence: IntArray

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    target = IntArray(N)
    br.readLine().split(" ").mapIndexed { index, given ->
        target[index] = given.toInt()
    }

    sequence = IntArray(N)
    br.readLine().split(" ").mapIndexed { index, given ->
        sequence[index] = given.toInt()
    }

    var count = 0
    var current = IntArray(N) { it }

    while (true) {
        if (isTarget(current)) {
            println(count)
            break
        }

        current = shuffle(current)
        count++

        if (hasNoAnswer(current)) {
            println(-1)
            break
        }
    }

    br.close()
}

fun isTarget(current: IntArray): Boolean {
    val result = IntArray(N)
    var count = 0

    for (i in current.indices) {
        result[current[i]] = (count % 3)
        count++
    }

    for (i in result.indices) {
        if (result[i] != target[i]) {
            return false
        }
    }

    return true
}

fun shuffle(current: IntArray): IntArray {
    val result = IntArray(N)

    for (i in current.indices) {
        result[sequence[i]] = current[i]
    }

    return result
}

fun hasNoAnswer(current: IntArray): Boolean {
    for (i in current.indices) {
        if (i != current[i]) {
            return false
        }
    }
    return true
}