package prob_2447

import kotlin.properties.Delegates

var N by Delegates.notNull<Int>()
//var M by Delegates.notNull<Int>()
//var K by Delegates.notNull<Int>()
//var result by Delegates.notNull<Int>()
//val sequence = mutableListOf<Int>()

fun main() {
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()

    var given = listOf("***", "* *", "***")

    while ((N / 3) != 1) {
        given = generate(given)
        N /= 3
    }

    given.forEach { println(it) }

    br.close();
}

fun generate(given: List<String>): List<String> {
    val blankNumber = given.size

    val result = mutableListOf<String>()

    for (i in 0..2) {
        if ((i % 3) == 1) {
            for (j in 0 until blankNumber) {
                result.add(given[j] + " ".repeat(blankNumber) + given[j])
            }
        } else {
            for (j in 0 until blankNumber) {
                result.add(given[j].repeat(3))
            }
        }
    }

    return result
}
