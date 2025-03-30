package prob_14499

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

lateinit var map: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    val given = br.readLine().split(" ")
    N = given[0].toInt()
    M = given[1].toInt()

    var curN = given[2].toInt()
    var curM = given[3].toInt()

    map = Array(N) { IntArray(M) }
    for (n in 0 until N) {
        val numbers = br.readLine().split(" ").map { it.toInt() }
        for (m in 0 until M) {
            map[n][m] = numbers[m]
        }
    }

    var dice = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))

    val commands = br.readLine().split(" ").map { it.toInt() }

    commands.forEach { command ->
        when (command) {
            1 -> {
                if (curM == M - 1) {
                    return@forEach
                }
                dice = east(dice)
                curM++
            }
            2 -> {
                if (curM == 0) {
                    return@forEach
                }
                dice = west(dice)
                curM--
            }
            3 -> {
                if (curN == 0) {
                    return@forEach
                }
                dice = north(dice)
                curN--
            }
            4 -> {
                if (curN == N - 1) {
                    return@forEach
                }
                dice = south(dice)
                curN++
            }
        }
        if (map[curN][curM] != 0) {
            dice[3][1] = map[curN][curM]
            map[curN][curM] = 0
        } else {
            map[curN][curM] = dice[3][1]
        }
        println(dice[1][1])
    }
    br.close();
}

fun south(dice: Array<IntArray>): Array<IntArray> {
    return arrayOf(
        intArrayOf(0, dice[3][1], 0),
        intArrayOf(dice[1][0], dice[0][1], dice[1][2]),
        intArrayOf(0, dice[1][1], 0),
        intArrayOf(0, dice[2][1], 0),
    )
}

fun north(dice: Array<IntArray>): Array<IntArray> {
    return arrayOf(
        intArrayOf(0, dice[1][1], 0),
        intArrayOf(dice[1][0], dice[2][1], dice[1][2]),
        intArrayOf(0, dice[3][1], 0),
        intArrayOf(0, dice[0][1], 0),
    )
}

fun east(dice: Array<IntArray>): Array<IntArray> {
    return arrayOf(
        intArrayOf(0, dice[0][1], 0),
        intArrayOf(dice[3][1], dice[1][0], dice[1][1]),
        intArrayOf(0, dice[2][1], 0),
        intArrayOf(0, dice[1][2], 0),
    )
}

fun west(dice: Array<IntArray>): Array<IntArray> {
    return arrayOf(
        intArrayOf(0, dice[0][1], 0),
        intArrayOf(dice[1][1], dice[1][2], dice[3][1]),
        intArrayOf(0, dice[2][1], 0),
        intArrayOf(0, dice[1][0], 0),
    )
}
