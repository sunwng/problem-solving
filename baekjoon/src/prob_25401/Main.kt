package prob_25401

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    val sequence = br.readLine().split(' ').map { it.toInt() }

    val map = hashMapOf<Int, Int>()

    sequence.forEach { number ->
        map[number] = map.getOrDefault(number, 0) + 1
    }

    var result = N - map.values.max()

    for (firstIdx in 0 until N) {

        for (secondIdx in firstIdx + 1 until N) {

            val diff = sequence[secondIdx] - sequence[firstIdx]

            if (diff % (secondIdx - firstIdx) != 0) continue

            val gap = diff / (secondIdx - firstIdx)

            result = minOf(result, count(firstIdx, sequence, gap))
        }

    }

    println(result)

    br.close();
}

fun count(baseIdx: Int, sequence: List<Int>, gap: Int): Int {
    var count = 0

    var current = sequence[baseIdx]
    for (i in baseIdx - 1 downTo  0) {
        current -= gap
        if (sequence[i] == current) continue
        count++
    }

    current = sequence[baseIdx]
    for (i in baseIdx + 1 until N) {
        current += gap
        if (sequence[i] == current) continue
        count++
    }

    return count
}