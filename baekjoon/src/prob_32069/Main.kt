package prob_32069

import java.util.LinkedList
import java.util.Queue
import kotlin.properties.Delegates

var L by Delegates.notNull<Long>()
var N by Delegates.notNull<Long>()
var K by Delegates.notNull<Long>()

fun main() {
    val br = System.`in`.bufferedReader()
    val given =  br.readLine().split(' ').map { it.toLong() }
    L = given[0]
    N = given[1]
    K = given[2]

    val lights = br.readLine().split(' ').map { it.toLong() }

    val queue:Queue<Location> = LinkedList()
    lights.forEach {
        queue.add(
            Location(
                index = it,
                degree = 0,
            )
        )
    }

    var count = 0L
    val visit = mutableSetOf<Long>()
    val sb = StringBuilder()

    while (queue.isNotEmpty() && count < K) {
        val current = queue.poll()
        if (visit.contains(current.index)) continue

        if (current.index - 1 >= 0 && visit.contains(current.index - 1).not()) {
            queue.add(
                Location(
                    index = current.index - 1,
                    degree = current.degree + 1,
                )
            )
        }
        if (current.index + 1 <= L && visit.contains(current.index + 1).not()) {
            queue.add(
                Location(
                    index = current.index + 1,
                    degree = current.degree + 1,
                )
            )
        }

        visit.add(current.index)
        sb.append("${current.degree}\n")
        count++
    }

    println(sb.toString())

    br.close();
}

data class Location(
    val index: Long,
    val degree: Long,
)