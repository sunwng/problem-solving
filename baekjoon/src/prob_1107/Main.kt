package prob_1107

import kotlin.math.absoluteValue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var buttons: BooleanArray

var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    M = br.readLine().toInt()

    buttons = BooleanArray(10) { true }
    if (M != 0) {
        br.readLine().split(" ").map {
            buttons[it.toInt()] = false
        }
    }

    if (N == 100) {
        println(0)
        return
    }

    result = (N - 100).absoluteValue
    search(0, 0)

    println(result)

    br.close();
}

fun search(idx: Int, clicked: Int) {
    for (i in 0..9) {
        if (buttons[i].not()) continue
        val newClicked = clicked * 10 + i
        result = minOf(result, (N - newClicked).absoluteValue + newClicked.toString().length)

        if (idx < 6) {
            search(idx + 1, newClicked)
        }
    }
}