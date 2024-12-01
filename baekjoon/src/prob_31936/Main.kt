package prob_31936

import kotlin.math.ceil
import kotlin.math.log2
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    val given = br.readLine().split(' ').map { it.toInt() }
    val counter = DoubleArray(N)

    var result = 0L

    for (idx in 1 until N) {
        val diff = ceil(log2(given[idx - 1].toDouble()) - log2(given[idx].toDouble())) + counter[idx - 1]

        if (diff > 0) {
            result += diff.toLong()
            counter[idx] = diff
        }
    }

    println(result)
    br.close();
}