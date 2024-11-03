package prob_9519

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    val given = br.readLine()

    var current = given
    var count = 0

    while (count < N) {
        current = getBefore(current)
        count++

        if (current == given) {
            break
        }
    }

    if (count < N) {
        repeat((N - count) % count) {
            current = getBefore(current)
        }
    }

    println(current)

    br.close();
}

fun getBefore(origin: String): String {
    var left = ""
    var right = ""

    for (idx in origin.indices) {
        if (idx % 2 == 0) {
            left = left + origin[idx]
        } else {
            right = origin[idx] + right
        }
    }

    return left + right
}
