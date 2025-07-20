package prob_3020

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var H by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    val given = br.readLine().split(" ").map { it.toInt() }
    N = given[0]
    H = given[1]

    val lower = IntArray(H + 1)
    val upper = IntArray(H + 1)

    for (i in 0 until N) {
        val height = br.readLine().toInt()
        when (i % 2) {
            0 -> {
                lower[height]++
            }

            1 -> {
                upper[height]++
            }
        }
    }

    val lowerCumulated = IntArray(H + 1)
    val upperCumulated = IntArray(H + 1)

    for (i in 1..H) {
        lowerCumulated[i] = lowerCumulated[i - 1] + lower[i]
        upperCumulated[i] = upperCumulated[i - 1] + upper[i]
    }

    var min = Int.MAX_VALUE
    var minCount = 0

    for (i in 1..H) {
        val lowerSum = lowerCumulated[H] - lowerCumulated[i - 1]
        val upperSum = upperCumulated[H] - upperCumulated[H - i]

        val sum = lowerSum + upperSum

        if (min > sum) {
            min = sum
            minCount = 1
        } else if (min == sum) {
            minCount++
        }
    }

    println("$min $minCount")

    br.close()
}