package prob_21314

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine()

    var mCounts = 0

    val max = StringBuilder()
    val min = StringBuilder()

    input.forEach { current ->

        if (current == 'M') {
            mCounts++
        } else {
            max.append(generateMax(mCounts))
            min.append(generateMin(mCounts))

            mCounts = 0
        }
    }

    if (mCounts != 0) {
        max.append("1".repeat(mCounts))
        min.append("1" + "0".repeat(mCounts - 1))
    }

    println(max.toString())
    println(min.toString())

    br.close()

}

fun generateMax(count: Int) : String {
    return "5" + "0".repeat(count)
}

fun generateMin(count: Int) : String {
    if(count == 0) return "5"

    return "1" + "0".repeat(count - 1) + "5"
}