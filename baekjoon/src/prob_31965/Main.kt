package prob_31965

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var Q by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    val given = br.readLine().split(' ').map { it.toInt() }
    N = given[0]
    Q = given[1]

    val accumulatedSum = LongArray(N + 1)
    val sequence = LongArray(N)
    br.readLine().split(' ')
        .forEachIndexed { idx, num ->
            sequence[idx] = num.toLong()
            accumulatedSum[idx + 1] = accumulatedSum[idx] + sequence[idx]
        }

    val chosens = mutableListOf<List<Long>>()
    repeat(Q) {
        chosens.add(br.readLine().split(' ').map { it.toLong() })
    }
    val result = StringBuilder()

    for (i in 0 until Q) {
        val chosen = chosens[i]
        var startIdx = sequence.binarySearch(chosen[0])
        if (startIdx < 0) {
            startIdx = startIdx.times(-1) - 1
        }

        var endIdx = sequence.binarySearch(chosen[1])
        if (endIdx < 0) {
            endIdx = endIdx.times(-1) - 2
        }

        if (startIdx >= endIdx) {
            result.append("0\n")
            continue
        }
        val max = maxOf(
            bigger(startIdx + 1, endIdx, sequence[startIdx], accumulatedSum),
            smaller(startIdx, endIdx - 1, sequence[endIdx], accumulatedSum).times(-1)
        )
        val count = endIdx - startIdx + 1
        val baseIdx = count / 2 + startIdx

        val min = bigger(baseIdx + 1, endIdx, sequence[baseIdx], accumulatedSum) - smaller(startIdx, baseIdx - 1, sequence[baseIdx], accumulatedSum)

        result.append("${max - min}\n")
    }
    println(result)

    br.close();
}

fun bigger(fromIdx: Int, endIdx: Int, base: Long, accumulatedSum: LongArray): Long {
    val count = endIdx - fromIdx + 1
    return accumulatedSum[endIdx + 1] - accumulatedSum[fromIdx] - count * base
}

fun smaller(fromIdx: Int, endIdx: Int, base: Long, accumulatedSum: LongArray): Long {
    val count = endIdx - fromIdx + 1
    return accumulatedSum[endIdx + 1] - accumulatedSum[fromIdx] - count * base
}