package prob_2590

fun main() {
    val br = System.`in`.bufferedReader()

    val papers = IntArray(7)

    for (i in 1..6) {
        val given = br.readLine().toInt()
        papers[i] = given
    }

    var result = 0

    result += papers[6]

    if (papers[5] > 0) {
        val count = papers[5]
        result += count

        if (papers[1] > 0) {
            papers[1] -= minOf(papers[1], 11 * count)
        }
    }

    if (papers[4] > 0) {
        val count = papers[4]
        result += count

        var vacancy = 20 * count
        if (papers[2] > 0) {
            val target = minOf(papers[2], count * 5)

            vacancy -= (4 * target)
            papers[2] -= target
        }

        if (vacancy > 0 && papers[1] > 0) {
            val target = minOf(papers[1], vacancy)
            papers[1] -= target
        }
    }

    if (papers[3] > 0) {
        result += (papers[3] / 4)
        val count = papers[3] % 4

        if (count != 0) {
            result++

            var vacancy = 36 - 9 * count

            when (count) {
                1 -> {
                    if (papers[2] > 0) {
                        val target = minOf(papers[2], 5)
                        papers[2] -= target
                        vacancy -= (4 * target)
                    }
                }
                2 -> {
                    if (papers[2] > 0) {
                        val target = minOf(papers[2], 3)
                        papers[2] -= target
                        vacancy -= (4 * target)
                    }
                }
                3 -> {
                    if (papers[2] > 0) {
                        papers[2]--
                        vacancy -= 4
                    }
                }
            }

            if (papers[1] > 0) {
                papers[1] -= minOf(papers[1], vacancy)
            }
        }
    }

    if (papers[2] > 0) {
        result += (papers[2] / 9)
        val count = papers[2] % 9

        if (count != 0) {
            result++

            val vacancy = 36 - 4 * count
            if (papers[1] > 0) {
                papers[1] -= minOf(papers[1], vacancy)
            }
        }
    }

    if (papers[1] > 0) {
        result += (papers[1] / 36)

        if (papers[1] % 36 != 0) {
            result++
        }
    }

    println(result)

    br.close()
}