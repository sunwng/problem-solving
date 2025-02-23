package prob_15686

import kotlin.math.abs
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    val numbers = br.readLine().split(" ").map { it.toInt() }
    N = numbers[0]
    M = numbers[1]


    val houses = mutableListOf<Pair<Int, Int>>()
    val chickens = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until N) {
        val given = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until N) {
            if (given[j] == 1) {
                houses.add(Pair(i, j))
            } else if (given[j] == 2) {
                chickens.add(Pair(i, j))
            }
        }
    }

    val combinations = mutableListOf<List<Int>>()
    getCombination(0, emptyList(), combinations, chickens.size)

    var result = Int.MAX_VALUE
    combinations.forEach { comb ->
        var sum = 0
        houses.forEach { house ->
            sum += calculate(house, comb, chickens)
        }
        result = minOf(result, sum)
    }
    println(result)

    br.close();
}

fun calculate(current: Pair<Int, Int>, combination: List<Int>, chickens: List<Pair<Int, Int>>): Int {
    var min = Int.MAX_VALUE

    combination.forEach {
        val sum = abs(current.first - chickens[it].first) + abs(current.second - chickens[it].second)
        min = minOf(min, sum)
    }
    return min
}

fun getCombination(current: Int, combination: List<Int>, result: MutableList<List<Int>>, max: Int) {
    if (combination.size == M) {
        result.add(combination)
        return
    }

    for (i in current until max) {
        getCombination(i + 1,combination + listOf(i), result, max)
    }
}