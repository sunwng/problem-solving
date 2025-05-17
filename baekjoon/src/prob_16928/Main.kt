package prob_16928

import java.util.*
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

lateinit var map: IntArray
lateinit var visit: BooleanArray


fun main() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(" ").let {
        N = it[0].toInt()
        M = it[1].toInt()
    }

    map = IntArray(101) { it }
    visit = BooleanArray(101) { false }

    repeat(N) {
        br.readLine().split(" ").let {
            map[it[0].toInt()] = it[1].toInt()
        }
    }
    repeat(M) {
        br.readLine().split(" ").let {
            map[it[0].toInt()] = it[1].toInt()
        }
    }

    val queue: Queue<Step> = LinkedList()
    queue.add(Step(current = 1, count = 0))
    visit[1] = true

    var result = 0
    while (queue.isNotEmpty()) {
        val case = queue.poll()

        if (case.current == 100) {
            result = case.count
            break
        }

        for (i in 1..6) {
            val next = case.current + i
            if (next > 100) continue
            if (visit[next]) continue

            visit[next] = true
            queue.add(Step(current = map[next], count = case.count + 1))
        }
    }

    println(result)

    br.close();
}

data class Step(
    val current: Int,
    val count: Int,
)