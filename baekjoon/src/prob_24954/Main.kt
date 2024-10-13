package prob_24954

import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val N: Int = br.readLine().toInt()
    val sequences = getPermutation(N)

    val prices = br.readLine().split(' ').map { it.toInt() }
    val discounts = Array(N) { IntArray(N) }

    for (idx in 0 until N) {
        val repeatTime = br.readLine().toInt()
        repeat(repeatTime) {
            val given = br.readLine().split(' ').map { it.toInt() }
            discounts[idx][given[0] - 1] = given[1]
        }
    }

    var result = Int.MAX_VALUE

    sequences.forEach { sequence ->
        var total = 0
        val discounted = IntArray(N)

        sequence.forEach { potion ->
            val price = cutMinimum(prices[potion] - discounted[potion])
            total += price
            discounts[potion].forEachIndexed { index, discount ->
                discounted[index] += discount
            }
        }
        result = min(result, total)
    }

    println(result)
    br.close();
}

fun cutMinimum(price: Int): Int {
    if (price <= 0) {
        return 1
    } else {
        return price
    }
}

fun getPermutation(size: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val current = mutableListOf<Int>()
    val used = BooleanArray(size) { false }

    fun backtrack() {
        if (current.size == size) {
            result.add(current.toList())
            return
        }

        for (idx in 0 until size) {
            if (used[idx]) continue

            current.add(idx)
            used[idx] = true
            backtrack()
            current.removeLast()
            used[idx] = false
        }
    }
    backtrack()
    return result
}