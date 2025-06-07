package prob_20056

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
var K by Delegates.notNull<Int>()

fun main() {
    val br = System.`in`.bufferedReader()

    br.readLine().split(" ").map { it.toInt() }.let {
        N = it[0]
        M = it[1]
        K = it[2]
    }

    val balls = mutableListOf<FireBall>()

    repeat(M) {
        val given = br.readLine().split(" ").map { it.toInt() }
        balls.add(
            FireBall(
                curR = given[0],
                curC = given[1],
                weight = given[2],
                speed = given[3],
                direction = given[4],
            )
        )
    }

    repeat(K) {
        move(balls)
    }

    val result = balls.sumOf { it.weight }

    println(result)

    br.close()
}

fun move(balls: MutableList<FireBall>) {
    val locations = mutableMapOf<Pair<Int, Int>, MutableList<FireBall>>()

    balls.forEach { ball ->
        ball.move()
        val pair = ball.getPair()

        if (locations.containsKey(pair)) {
            locations[pair]!!.add(ball)
        } else {
            locations[pair] = mutableListOf(ball)
        }
    }

    for ((pair, aggregatedBalls) in locations) {
        if (aggregatedBalls.size == 1) continue

        val weight = aggregatedBalls.sumOf { it.weight }

        aggregatedBalls.forEach { balls.remove(it) }
        if (weight < 5) {
            continue
        }

        val speed = aggregatedBalls.sumOf { it.speed }

        var oddCount = 0
        var evenCount = 0

        for (aggregatedBall in aggregatedBalls) {
            when (aggregatedBall.direction % 2) {
                0 -> oddCount++
                1 -> evenCount++
            }
        }

        var startDirection = 0

        if (oddCount != 0 && evenCount != 0) {
            startDirection = 1
        }

        for (i in 0..3) {
            balls.add(
                FireBall(
                    curR = pair.first,
                    curC = pair.second,
                    weight = weight / 5,
                    speed = speed / aggregatedBalls.size,
                    direction = startDirection + (2 * i),
                )
            )
        }
    }
}

data class FireBall(
    var curR: Int,
    var curC: Int,
    var weight: Int,
    val speed: Int,
    val direction: Int,
) {
    fun getPair() = Pair(curR, curC)

    fun move() {
        val nextR = (dirR[direction] * speed + curR) % N
        val nextC = (dirC[direction] * speed + curC) % N

        if (nextR < 0) {
            curR = nextR + N
        } else {
            curR = nextR
        }

        if (nextC < 0) {
            curC = nextC + N
        } else {
            curC = nextC
        }
    }

    companion object {
        val dirR = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
        val dirC = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    }
}