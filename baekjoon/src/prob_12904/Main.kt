package prob_12904

fun main() {
    val br = System.`in`.bufferedReader()
    // br.readLine().split(' ').map { it.toInt() }
    val S: String = br.readLine()
    val T: String = br.readLine()

    var result = 0
    var removed = T

    while (removed.length >= S.length) {
        if (removed.last() == 'A') {
            removed = removed.dropLast(1)
        } else { // 'B'
            removed = removed.dropLast(1).reversed()
        }

        if (removed == S) {
            result = 1
            break
        }
    }
    println(result)

    br.close();
}