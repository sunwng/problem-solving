package prob_11000

import java.util.*
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    val lectures = PriorityQueue(
        compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
    )

    repeat(N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        lectures.add(given[0] to given[1])
    }

    val rooms = PriorityQueue<Int>()
    rooms.add(0)

    while (lectures.isNotEmpty()) {
        val lecture = lectures.poll()
        if (lecture.first >= rooms.peek()) {
            rooms.poll()
        }
        rooms.add(lecture.second)
    }

    println(rooms.size)

    br.close()
}