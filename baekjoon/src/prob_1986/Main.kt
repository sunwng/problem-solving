package prob_1986

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<Array<Int>>

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine().split(' ').map { it.toInt() }
    N = input[0]
    M = input[1]

    map = Array(N) { Array(M) { -1 } }

    val queenInfo = br.readLine().split(' ').map { it.toInt() }
    for (idx in 1..queenInfo[0]) {
        val n = idx * 2 - 1
        val m = idx * 2
        map[queenInfo[n] - 1][queenInfo[m] - 1] = 3
    }

    val knightInfo = br.readLine().split(' ').map { it.toInt() }
    for (idx in 1..knightInfo[0]) {
        val n = idx * 2 - 1
        val m = idx * 2
        map[knightInfo[n] - 1][knightInfo[m] - 1] = 2
    }

    val pawnInfo = br.readLine().split(' ').map { it.toInt() }
    for (idx in 1..pawnInfo[0]) {
        val n = idx * 2 - 1
        val m = idx * 2
        map[pawnInfo[n] - 1][pawnInfo[m] - 1] = 1
    }

    for (n in 0..<N) {
        for (m in 0..<M) {
            if (map[n][m] <= 1) {
                continue
            } else if (map[n][m] == 2) {
                checkKnight(n, m)
            } else {
                checkQueen(n, m)
            }
        }
    }

    var answer = 0
    for (n in 0..<N) {
        for (m in 0..<M) {
            if (map[n][m] > -1) continue
            answer++
        }
    }

    println(answer)
    br.close()
}

fun checkKnight(currentN: Int, currentM: Int) {
    val nextN = arrayOf(-2, -2, -1, -1, 1, 1, 2, 2)
    val nextM = arrayOf(1, -1, 2, -2, 2, -2, 1, -1)

    for (idx in 0..7) {
        val targetN = currentN + nextN[idx]
        val targetM = currentM + nextM[idx]
        if (checkIsOut(targetN, targetM) || checkIsNotAvailable(targetN, targetM)) continue

        map[targetN][targetM] = 0
    }
}

fun checkQueen(currentN: Int, currentM: Int) {
    val nextN = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
    val nextM = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)

    for (idx in 0..7) {
        var targetN = currentN
        var targetM = currentM
        while (true) {
            targetN += nextN[idx]
            targetM += nextM[idx]

            if (checkIsOut(targetN, targetM) || checkIsNotAvailable(targetN, targetM)) break

            map[targetN][targetM] = 0
        }
    }

}

fun checkIsNotAvailable(targetN: Int, targetM: Int): Boolean {
    return map[targetN][targetM] > 0
}

fun checkIsOut(targetN: Int, targetM: Int): Boolean {
    return targetN < 0 || targetN >= N || targetM < 0 || targetM >= M
}