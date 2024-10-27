package prob_2469

import kotlin.properties.Delegates

var N_PEOPLE by Delegates.notNull<Int>()
var N_LINES by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N_PEOPLE = br.readLine().toInt()
    N_LINES = br.readLine().toInt()

    val alphabet = ('A'..'Z').toList()

    val start = alphabet.take(N_PEOPLE).toCharArray()
    val target = br.readLine().toCharArray()

    val upper = mutableListOf<String>()
    val lower = mutableListOf<String>()

    var missedLine = -1

    for (idx in 0 until N_LINES) {
        val given = br.readLine()
        if (given[0] == '?') {
            missedLine = idx
            continue
        }

        if (missedLine == -1) {
            upper.add(given)
        } else {
            lower.add(given)
        }
    }

    val before = getResult(start, upper)
    val after = getResult(target, lower.reversed())

    var result = ""
    for (idx in 0 until N_PEOPLE) {
        if (before[idx] == after[idx]) {
            if (result.length < N_PEOPLE - 1) {
                result += "*"
            }
            continue
        }

        if (idx == 0) {
            if (before[idx] == after[idx + 1]) {
                result += "-"
                continue
            }
        } else if (idx == N_PEOPLE - 1) {
            if (before[idx] == after[idx - 1]) {
                if (result.last() == '-') {
                    continue
                }
                continue
            }
        } else {
            if (before[idx] == after[idx - 1]) {
                if (result.last() == '-') {
                    result += "*"
                    continue
                }
            } else if (before[idx] == after[idx + 1]) {
                result += '-'
                continue
            }
        }

        println("x".repeat(N_PEOPLE - 1))
        return
    }
    println(result)

    br.close();
}

fun getResult(origin: CharArray, lines: List<String>): CharArray {
    val resultIdx = mutableListOf<Int>()

    for (idx in 0 until N_PEOPLE) {
        var changedIdx = idx
        lines.forEach { line ->
            if (changedIdx == 0) {
                val right = checkDirection(changedIdx, line)
                if (right) changedIdx++
            } else if (changedIdx == N_PEOPLE - 1) {
                val left = checkDirection(changedIdx - 1, line)
                if (left) changedIdx--
            } else {
                val left = checkDirection(changedIdx - 1, line)
                val right = checkDirection(changedIdx, line)
                when {
                    left -> changedIdx--
                    right -> changedIdx++
                }
            }
        }
        resultIdx.add(changedIdx)
    }
    val result = CharArray(origin.size)
    resultIdx.forEachIndexed() { originIdx, changedIdx ->
        result[changedIdx] = origin[originIdx]
    }
    return result
}

fun checkDirection(idx: Int, line: String): Boolean {
    return line[idx] == '-'
}
