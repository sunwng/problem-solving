package prob_9205

import kotlin.math.absoluteValue
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    N = br.readLine().toInt()

    val result = mutableListOf<String>()

    repeat(N) {
        val C = br.readLine().toInt()

        val places = mutableListOf<List<Int>>()

        places.add(br.readLine().split(" ").map { it.toInt() })

        for (i in 0 until C) {
            places.add(br.readLine().split(" ").map { it.toInt() })
        }

        places.add(br.readLine().split(" ").map { it.toInt() })

        val parent = IntArray(C + 2) { it }

        for (i in places.indices) {
            for (j in i + 1 until places.size) {
                val distance = getDistance(places[i], places[j])
                if (distance <= 1000) {
                    union(i, j, parent)
                }
            }
        }

        val parentHome = find(0, parent)
        val parentDestination = find(places.lastIndex, parent)

        if (parentHome == parentDestination) {
            result.add("happy")
        } else {
            result.add("sad")
        }
    }

    println(result.joinToString("\n"))

    br.close()
}

fun union(x: Int, y: Int, parent: IntArray) {
    val parentX = find(x, parent)
    val parentY = find(y, parent)

    if (parentX <= parentY) {
        parent[parentY] = parentX
    } else {
        parent[parentX] = parentY
    }
}

fun find(target: Int, parent: IntArray): Int {
    if (target != parent[target]) {
        parent[target] = find(parent[target], parent)
    }

    return parent[target]
}

fun getDistance(from: List<Int>, to: List<Int>): Int {
    return (from[0] - to[0]).absoluteValue + (from[1] - to[1]).absoluteValue
}