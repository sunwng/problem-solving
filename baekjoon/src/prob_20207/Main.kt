package prob_20207

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    val schedules = mutableListOf<Schedule>()
    repeat(N) {
        val given = br.readLine().split(' ')
        schedules.add(Schedule(given[0].toInt(), given[1].toInt()))
    }

    schedules.sortWith(
        compareBy<Schedule> { it.start }
            .thenByDescending { it.getLength() }
    )

    val occupyChecker = Array(367) { 0 }

    schedules.forEach { schedule ->
        for (idx in schedule.start..schedule.end) {
            occupyChecker[idx]++
        }
    }

    var result = 0
    var max = 0
    var length = 0

    occupyChecker.forEach { value ->
        if (value == 0) {
            result += (max * length)
            length = 0
            max = 0
        } else {
            max = maxOf(max, value)
            length++
        }
    }

    println(result)

    br.close();
}

data class Schedule(
    val start: Int,
    val end: Int,
) {
    fun getLength() = end - start + 1
}