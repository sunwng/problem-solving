package prob_2170

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()


fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    val sequence = mutableListOf<Pair<Int, Int>>()

    repeat(N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        sequence.add(given[0] to given[1])
    }

    sequence.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

    var result = 0

    var left = -1_000_000_001
    var right = -1_000_000_001

    for (i in sequence.indices) {
        val current = sequence[i]

        if (current.first >= left && current.second <= right) {
            continue
        }

        if (current.first > right) {
            result += (right - left)
            left = current.first
            right = current.second
        } else {
            right = current.second
        }
    }

    result += (right - left)

    println(result)

    br.close()
}