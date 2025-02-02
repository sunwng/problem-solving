package prob_10026

import java.util.LinkedList
import java.util.Queue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    val map = Array(N) { CharArray(N) }

    for (i in 0 until N) {
        val given = br.readLine().toCharArray()
        for (j in 0 until N) {
            map[i][j] = given[j]
        }
    }

    var normal = 0
    val normalVisit = Array(N) { BooleanArray(N) }
    var blind = 0
    val blindVisit = Array(N) { BooleanArray(N) }

    for (i in 0 until N) {
        for (j in 0 until N) {
            if (normalVisit[i][j]) continue
            search(i, j, map, normalVisit)
            normal++
        }
    }
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (blindVisit[i][j]) continue
            searchForBlind(i, j, map, blindVisit)
            blind++
        }
    }

    println("$normal $blind")

    br.close();
}

fun search(row: Int, col: Int, map: Array<CharArray>, visit: Array<BooleanArray>) {
    val selectedColor = map[row][col]

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(row, col))

    val dr = arrayOf(-1, 1, 0, 0)
    val dc = arrayOf(0, 0, -1, 1)

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (i in 0 ..3) {
            val nextR = current.first + dr[i]
            val nextC = current.second + dc[i]

            if (isOut(nextR, nextC)) continue
            if (visit[nextR][nextC]) continue
            if (map[nextR][nextC] != selectedColor) continue

            queue.add(Pair(nextR, nextC))
            visit[nextR][nextC] = true
        }
    }
}

fun searchForBlind(row: Int, col: Int, map: Array<CharArray>, visit: Array<BooleanArray>) {
    val selectedColor = map[row][col]

    val colorSet = mutableSetOf(selectedColor)
    if (selectedColor == 'R') {
        colorSet.add('G')
    } else if (selectedColor == 'G') {
        colorSet.add('R')
    }

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(row, col))

    val dr = arrayOf(-1, 1, 0, 0)
    val dc = arrayOf(0, 0, -1, 1)

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (i in 0 ..3) {
            val nextR = current.first + dr[i]
            val nextC = current.second + dc[i]

            if (isOut(nextR, nextC)) continue
            if (visit[nextR][nextC]) continue
            if (map[nextR][nextC] !in colorSet) continue

            queue.add(Pair(nextR, nextC))
            visit[nextR][nextC] = true
        }
    }
}

fun isOut(row:Int, col: Int): Boolean {
    return row < 0 || row >= N || col < 0 || col >= N
}