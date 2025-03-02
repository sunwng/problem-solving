package prob_5430

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    val output = StringBuilder()

    for (i in 0 until N) {
        val sequence = br.readLine().toCharArray()
        val length = br.readLine().toInt()
        val givenString = br.readLine().removeSurrounding("[", "]")

        if (sequence.count { it == 'D' } > length) {
            output.append("error")
            if (i != N - 1) {
                output.append("\n")
            }
            continue
        }
        if (givenString.isEmpty()) {
            output.append("[]")
            if (i != N - 1) {
                output.append("\n")
            }
            continue
        }
        val given = givenString.split(",")

        var direction = true
        var leftIdx = 0
        var rightIdx = length - 1

        sequence.forEach {
            when(it) {
                'D' -> {
                    if (direction) {
                        leftIdx++
                    } else {
                        rightIdx--
                    }
                }
                'R' -> {
                    direction = direction.not()
                }
            }
        }

        val result = mutableListOf<String>()
        if (direction) {
            for (idx in leftIdx..rightIdx) {
                result.add(given[idx])
            }
        } else {
            for (idx in rightIdx downTo leftIdx) {
                result.add(given[idx])
            }
        }
        output.append("[" + result.joinToString(",") + "]")

        if (i != N - 1) {
            output.append("\n")
        }
    }
    println(output.toString())

    br.close();
}