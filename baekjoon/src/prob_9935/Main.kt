package prob_9935

import java.util.*

lateinit var sequence: String
lateinit var bomb: String

fun main() {
    val br = System.`in`.bufferedReader()

    sequence = br.readLine()
    bomb = br.readLine()

    val stack = Stack<Char>()

    for (current in sequence) {
        stack.push(current)

        if (current != bomb.last()) continue

        if (stack.size < bomb.length) continue

        var check = true
        for (i in bomb.indices) {
            if (stack[stack.size - i - 1] != bomb[bomb.length - i - 1]) {
                check = false
                break
            }
        }

        if (check.not()) continue

        repeat(bomb.length) {
            stack.pop()
        }
    }


    val sb = StringBuilder()

    while(stack.isNotEmpty()) {
        sb.append(stack.pop())
    }

    if (sb.isEmpty()) println("FRULA")
    else println(sb.toString().reversed())

    br.close();
}