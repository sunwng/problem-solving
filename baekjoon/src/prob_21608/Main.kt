package prob_21608

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

lateinit var likeMap: Array<BooleanArray>
lateinit var map: Array<IntArray>

var result by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    likeMap = Array(N * N + 1) { BooleanArray(N * N + 1) { false } }
    map = Array(N) { IntArray(N) }

    val sequence = mutableListOf<Int>()

    repeat(N * N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        sequence.add(given[0])
        likeMap[given[0]][given[1]] = true
        likeMap[given[0]][given[2]] = true
        likeMap[given[0]][given[3]] = true
        likeMap[given[0]][given[4]] = true
    }

    val candidates = mutableListOf<Candidate>()

    for (current in sequence) {
        find(current = current, candidates = candidates)

        candidates.sortWith(
            compareByDescending<Candidate> { it.likes }
                .thenByDescending { it.empties }
                .thenBy { it.r }
                .thenBy { it.c }
        )

        val selected = candidates[0]
        map[selected.r][selected.c] = current

        candidates.clear()
    }

    var result = 0

    for (r in 0 until N) {
        for (c in 0 until N) {
            val count = countLikes(r, c, map[r][c])

            when (count) {
                1 -> {
                    result += 1
                }
                2 -> {
                    result += 10
                }
                3 -> {
                    result += 100
                }
                4 -> {
                    result += 1000
                }
            }
        }
    }

    println(result)

    br.close();
}

fun find(current: Int, candidates: MutableList<Candidate>) {
    for (r in 0 until N) {
        for (c in 0 until N) {
            if (map[r][c] != 0) continue

            val candidate = count(r = r, c = c, current = current)
            candidates.add(candidate)
        }
    }
}

val dirR = arrayOf(0, 0, -1, 1)
val dirC = arrayOf(-1, 1, 0, 0)

fun isOut(r: Int, c: Int) = r < 0 || r >= N || c < 0 || c >= N

fun count(r: Int, c: Int, current: Int): Candidate {
    var emptyCount = 0
    var likeCount = 0

    for (i in 0..3) {
        val nextR = r + dirR[i]
        val nextC = c + dirC[i]
        if (isOut(nextR, nextC)) continue

        if (map[nextR][nextC] == 0) {
            emptyCount++
        }
        if (likeMap[current][map[nextR][nextC]]) {
            likeCount++
        }
    }

    return Candidate(
        r = r,
        c = c,
        likes = likeCount,
        empties = emptyCount
    )
}

fun countLikes(r: Int, c: Int, current: Int): Int {
    var likeCount = 0

    for (i in 0..3) {
        val nextR = r + dirR[i]
        val nextC = c + dirC[i]
        if (isOut(nextR, nextC)) continue
        if (likeMap[current][map[nextR][nextC]]) {
            likeCount++
        }
    }

    return likeCount
}

data class Candidate(
    val r: Int,
    val c: Int,
    val likes: Int,
    val empties: Int,
)