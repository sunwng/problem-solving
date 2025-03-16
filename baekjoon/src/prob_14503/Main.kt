package prob_14503

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>

val dirN = arrayOf(-1, 0, 1, 0)
val dirM = arrayOf(0, -1, 0, 1)

fun main() {
    val br = System.`in`.bufferedReader()
    var given = br.readLine().split(" ").map { it.toInt() }
    N = given[0]
    M = given[1]

    given = br.readLine().split(" ").map { it.toInt() }
    var curN = given[0]
    var curM = given[1]
    var curDir = initDir(given[2])

    map = Array(N) { IntArray(M) }

    for (n in 0 until N) {
        given = br.readLine().split(" ").map { it.toInt() }
        for (m in 0 until M) {
            map[n][m] = given[m]
        }
    }

    var result = 0
    while(true) {
        if (isDone(curN, curM).not()) {
            result++
            map[curN][curM] = 2
        }

        when (isAvailable(curN, curM)) {
            true -> {
                curDir = changeDirection(curDir)
                val possibleN = curN + dirN[curDir]
                val possibleM = curM + dirM[curDir]

                if (canGo(possibleN, possibleM)) {
                    curN = possibleN
                    curM = possibleM
                }
                continue
            }
            false -> {
                curN -= dirN[curDir]
                curM -= dirM[curDir]
                if (isWall(curN, curM)) {
                    break
                }
                continue
            }
        }
    }

    println(result)


    br.close();
}

fun canGo(targetN: Int, targetM: Int): Boolean {
    return isWall(targetN, targetM).not() && isDone(targetN, targetM).not()
}

fun changeDirection(curDir: Int): Int {
    return (curDir + 1) % 4
}

fun isWall(targetN: Int, targetM: Int): Boolean {
    return map[targetN][targetM] == 1
}

fun isDone(targetN: Int, targetM: Int): Boolean {
    return map[targetN][targetM] == 2
}

fun isOut(targetN: Int, targetM: Int): Boolean {
    return targetN < 0 || targetN >= N || targetM < 0 || targetM >= M
}

fun isAvailable(targetN: Int, targetM: Int): Boolean {
    for (i in 0..3) {
        val nextN = targetN + dirN[i]
        val nextM = targetM + dirM[i]

        if (isOut(nextN, nextM)) continue
        if (isWall(nextN, nextM).not() && isDone(nextN, nextM).not()) {
            return true
        }
    }
    return false
}

fun initDir(given: Int): Int {
    return when(given) {
        0 -> 0
        1 -> 3
        2 -> 2
        3 -> 1
        else -> throw IllegalArgumentException()
    }
}