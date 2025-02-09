package prob_14500

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    val numbers = br.readLine().split(" ").map { it.toInt() }
    N = numbers[0]
    M = numbers[1]

    map = Array(N) { IntArray(M) }

    for (i in 0 until N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until M) {
            map[i][j] = given[j]
        }
    }

    var max = Int.MIN_VALUE

    for (i in 0 until N) {
        for (j in 0 until M) {
            max = maxOf(max, findFinger(i, j))
            max = maxOf(max, findLightning(i, j))
            max = maxOf(max, findNiun(i, j))
            max = maxOf(max, findSquare(i, j))
            max = maxOf(max, findStick(i, j))
        }
    }

    println(max)

    br.close();
}

fun findFinger(startN: Int, startM: Int): Int {
    var result = Int.MIN_VALUE

    val dirN = arrayOf(
        arrayOf(1, 2, 1),
        arrayOf(1, 2, 1),
        arrayOf(0, 0, 1),
        arrayOf(0, 0, -1),
    )
    val dirM = arrayOf(
        arrayOf(0, 0, 1),
        arrayOf(0, 0, -1),
        arrayOf(1, 2, 1),
        arrayOf(1, 2, 1),
    )

    for (i in 0..3) {
        var sum = 0

        for (j in 0..2) {
            val curN = startN + dirN[i][j]
            val curM = startM + dirM[i][j]
            if (isOut(curN, curM)) {
                break
            }
            sum += map[curN][curM]
        }
        result = maxOf(result, sum)
    }
    return result + map[startN][startM]
}

fun findLightning(startN: Int, startM: Int): Int {
    var result = Int.MIN_VALUE

    val dirN = arrayOf(
        arrayOf(1, 0, 1),
        arrayOf(1, 0, 1),
        arrayOf(0, 1, 0),
        arrayOf(0, -1, 0),
    )
    val dirM = arrayOf(
        arrayOf(0, 1, 0),
        arrayOf(0, -1, 0),
        arrayOf(1, 0, 1),
        arrayOf(1, 0, 1),
    )

    for (i in 0..3) {
        var sum = 0

        var curN = startN
        var curM = startM

        for (j in 0..2) {
            curN += dirN[i][j]
            curM += dirM[i][j]
            if (isOut(curN, curM)) {
                break
            }
            sum += map[curN][curM]
        }
        result = maxOf(result, sum)
    }
    return result + map[startN][startM]
}

fun findNiun(startN: Int, startM: Int): Int {
    var result = Int.MIN_VALUE

    val dirN = arrayOf(
        arrayOf(1, 1, 0),
        arrayOf(1, 1, 0),
        arrayOf(0, 0, 1),
        arrayOf(0, 0, -1),
        arrayOf(-1, -1, 0),
        arrayOf(-1, -1, 0),
        arrayOf(0, 0, 1),
        arrayOf(0, 0, -1),
    )
    val dirM = arrayOf(
        arrayOf(0, 0, 1),
        arrayOf(0, 0, -1),
        arrayOf(1, 1, 0),
        arrayOf(1, 1, 0),
        arrayOf(0, 0, 1),
        arrayOf(0, 0, -1),
        arrayOf(-1, -1, 0),
        arrayOf(-1, -1, 0),
    )

    for (i in 0..7) {
        var sum = 0

        var curN = startN
        var curM = startM

        for (j in 0..2) {
            curN += dirN[i][j]
            curM += dirM[i][j]
            if (isOut(curN, curM)) {
                break
            }
            sum += map[curN][curM]
        }
        result = maxOf(result, sum)
    }
    return result + map[startN][startM]
}

fun findSquare(startN: Int, startM: Int): Int {
    var result = 0
    val dirN = arrayOf(1, 0, 1)
    val dirM = arrayOf(0, 1, 1)

    for (i in 0..2) {
        val curN = startN + dirN[i]
        val curM = startM + dirM[i]
        if (isOut(curN, curM)) {
            return 0
        }
        result += map[curN][curM]
    }

    return result + map[startN][startM]
}

fun findStick(startN: Int, startM: Int): Int {
    var result = Int.MIN_VALUE
    val dirN = arrayOf(1, 0)
    val dirM = arrayOf(0, 1)

    for (i in 0..1) {
        var curN = startN
        var curM = startM
        var sum = 0
        for (j in 0..2) {
            curN += dirN[i]
            curM += dirM[i]
            if (isOut(curN, curM)) {
                break
            }
            sum += map[curN][curM]
        }
        result = maxOf(result, sum)
    }
    return result + map[startN][startM]
}

fun isOut(givenN: Int, givenM: Int): Boolean {
    return givenN < 0 || givenN >= N || givenM < 0 || givenM >= M
}
