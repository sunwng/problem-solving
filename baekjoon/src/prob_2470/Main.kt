package prob_2470

import kotlin.math.absoluteValue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

lateinit var map: Array<IntArray>

var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    val given = br.readLine().split(" ").map { it.toLong() }
    val sequence = given.sorted()

    var current = Long.MAX_VALUE
    val result = mutableListOf(0L, 0L)

    for (i in sequence.indices) {
        val idx = sequence.binarySearch(sequence[i] * -1)

        if (idx > 0) {
            result.clear()
            result.add(sequence[i])
            result.add(sequence[idx])
            break
        } else {
            val targetIdx = (idx + 1) * -1

            if (targetIdx < N) {
                if (sequence[i] == sequence[targetIdx]) continue
                val temp = (sequence[i] + sequence[targetIdx]).absoluteValue
                if (temp < current) {
                    current = temp
                    result.clear()
                    result.add(sequence[i])
                    result.add(sequence[targetIdx])
                }
            }
            if (targetIdx > 0) {
                if (sequence[i] == sequence[targetIdx - 1]) continue
                val temp = (sequence[i] + sequence[targetIdx - 1]).absoluteValue
                if (temp < current) {
                    current = temp
                    result.clear()
                    result.add(sequence[i])
                    result.add(sequence[targetIdx - 1])
                }
            }
        }
    }

    result.sort()

    println(result.joinToString(" "))
    br.close();
}
