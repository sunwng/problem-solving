package prob_1715

import java.util.PriorityQueue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    val sequence: PriorityQueue<Long> = PriorityQueue()

    for (i in 0 until N) {
        val idx = br.readLine().toLong()
        sequence.add(idx)
    }

    var result = 0L

    while(sequence.isNotEmpty()) {
        val first = sequence.poll()
        var sum = first

        if (sequence.isEmpty()) break

        val second = sequence.poll()
        sum += second
        result += sum

        if (sequence.isEmpty()) break
        sequence.add(sum)
    }

    println(result)

    br.close();
}