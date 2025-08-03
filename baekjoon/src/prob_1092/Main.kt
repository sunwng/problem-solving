package prob_1092

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()
    val cranes = mutableListOf<Int>()
    br.readLine().split(" ").forEach { cranes.add(it.toInt()) }
    cranes.sortDescending()

    M = br.readLine().toInt()

    val boxes = mutableListOf<Int>()
    br.readLine().split(" ").forEach { boxes.add(it.toInt()) }
    boxes.sortDescending()

    if (boxes.first() > cranes.first()) {
        println(-1)
        return
    }

    var time = 0
    while (boxes.isNotEmpty()) {
        var boxIdx = 0
        for (crane in cranes) {
            while (boxIdx < boxes.size) {
                if (boxes[boxIdx] <= crane) {
                    boxes.removeAt(boxIdx)
                    break
                }
                boxIdx++
            }
        }

        time++
    }

    println(time)

    br.close()
}
