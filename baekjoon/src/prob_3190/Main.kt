package prob_3190

import java.util.LinkedList
import java.util.Queue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var APPLE by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>
val direction = arrayOf(
    arrayOf(0, 1),
    arrayOf(1, 0),
    arrayOf(0, -1),
    arrayOf(-1, 0),
)

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    APPLE = br.readLine().toInt()

    map = Array(N) { IntArray(N) }
    repeat(APPLE) {
        val given = br.readLine().split(" ").map { it.toInt() - 1 }
        map[given[0]][given[1]] = 2
    }

    val commandNum = br.readLine().toInt()
    val directionCommand = CharArray(10001) { ' ' }

    repeat(commandNum) {
        val given = br.readLine().split(" ")
        directionCommand[given[0].toInt()] = given[1][0]
    }

    var directionIdx = 0
    var time = 0
    var currentX = 0
    var currentY = 0

    map[0][0] = 1
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(0, 0))

    while (true) {
        time++

        if (isFinished(currentX, currentY, directionIdx)) {
            println(time)
            break
        }
        currentX += direction[directionIdx][0]
        currentY += direction[directionIdx][1]
        queue.add(Pair(currentX, currentY))

        if (map[currentX][currentY] != 2) {
            val tail = queue.poll()
            map[tail.first][tail.second] = 0
        }
        map[currentX][currentY] = 1

        if (directionCommand[time] != ' ') {
            when (directionCommand[time]) {
                'L' -> directionIdx = counterClockWise(directionIdx)
                'D' -> directionIdx = clockWise(directionIdx)
            }
        }
    }

    br.close();
}

fun clockWise(dirIdx: Int) = (dirIdx + 1) % 4

fun counterClockWise(dirIdx: Int): Int {
    val next = dirIdx - 1
    if (next < 0) return 3
    else return next
}

fun isFinished(curX: Int, curY: Int, dirInx: Int): Boolean {
    if (curX + direction[dirInx][0] < 0 || curX + direction[dirInx][0] >= N) return true
    if (curY + direction[dirInx][1] < 0 || curY + direction[dirInx][1] >= N) return true
    if (map[curX + direction[dirInx][0]][curY + direction[dirInx][1]] == 1) return true
    return false
}