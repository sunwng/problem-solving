package prob_28325

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    val given = br.readLine().split(' ').map { it.toInt() }
    N = given[0]

    val sequence = br.readLine().split(' ')

    var result = 0L
    var firstIdx = -1
    var lastIdx = -1
    var count = 0

    sequence.forEachIndexed { idx, it ->
        val num = it.toLong()
        if (num != 0L) {
            if (firstIdx == -1) {
                firstIdx = idx
            } else {
                val total = count(lastIdx, idx)
                result += (total / 2 + total % 2)
            }
            lastIdx = idx
            result += num
        } else {
            count++
        }
    }

    if (count == N) {
        println(count / 2)
        return
    }

    val total = count(lastIdx, firstIdx)
    result += (total / 2 + total % 2)
    println(result)

    br.close();
}

fun count(from: Int, to: Int): Int {
    return if (to > from) {
        to - from - 1
    } else if (to < from) {
        (N - from - 1) + (to)
    } else {
        N - 1
    }
}