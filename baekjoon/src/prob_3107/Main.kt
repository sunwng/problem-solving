package prob_3107

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine()

    val list = mutableListOf<String>()

    var before = '-'
    var rebuilt = ""

    for (char in input) {
        val current = char

        if (current == ':') {
            if (before == ':') {
                list.add("-1")
            } else {
                if (rebuilt.isBlank()) {
                    before = char
                    continue
                }

                list.add(rebuilt.padStart(4, '0'))
                rebuilt = ""
            }
        } else {
            rebuilt += current
        }

        before = current
    }

    if (rebuilt.isNotBlank()) {
        list.add(rebuilt.padStart(4, '0'))
    }

    val result = mutableListOf<String>()

    list.forEach { group ->
        if (group == "-1") {
            val cycle = 8 - list.size + 1
            repeat(cycle) {
                result.add("0000")
            }
        } else {
            result.add(group)
        }
    }
    println(result.joinToString(":"))

    br.close()

}
