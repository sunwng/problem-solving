package prob_17298

import java.util.*
import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
var M by Delegates.notNull<Int>()
lateinit var map: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    val sequence = br.readLine().split(" ").map { it.toInt() }
    val stack = Stack<Int>()

    val answer = IntArray(N)

    for (i in N - 1 downTo 0) {

        while (stack.isEmpty().not() && sequence[i] >= stack.peek()) {
            stack.pop()
        }

        if (stack.isEmpty()) {
            answer[i] = -1
        } else {
            answer[i] = stack.peek()
        }

        stack.push(sequence[i])
    }
    println(answer.joinToString(" "))

    br.close();
}