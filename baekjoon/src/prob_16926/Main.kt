package prob_16926

fun main() {
    val br = System.`in`.bufferedReader()
    val S = br.readLine().split(' ').map { it.toInt() }

    val N: Int = S[0]
    val M: Int = S[1]
    val R: Int = S[2]

    val original = Array(N) { IntArray(M) }
    for (i in 0 until N) {
        original[i] = br.readLine().split(' ').map { it.toInt() }.toIntArray()
    }

    val sequences = mutableListOf<MutableList<Int>>()
    sequences.add(mutableListOf())
    var count = 0
    var n = 0
    var m = 0
    var nMin = 0
    var nMax = N - 1
    var mMin = 0
    var mMax = M - 1

    while (true) {
        sequences.last().add(original[n][m])
        count++
        if (n == nMin && m != mMax) {
            m++
        } else if (n != nMax && m == mMax) {
            n++
        } else if (n == nMax && m != mMin) {
            m--
        } else if (n != nMin && m == mMin) {
            n--
        } else {
            throw IllegalArgumentException()
        }

        if (count == N * M) break

        if (n == nMin && m == mMin) {
            n++
            m++
            nMin++
            mMin++
            nMax--
            mMax--
            sequences.add(mutableListOf())
        }
    }

    val startPoints = IntArray(sequences.size)

    repeat(R) {
        sequences.forEachIndexed{ idx, sequence ->
            startPoints[idx] = getNext(startPoints[idx], sequence.size)
        }
    }

    val rotated = Array(N) { IntArray(M) }
    count = 0
    n = 0
    m = 0
    nMin = 0
    nMax = N - 1
    mMin = 0
    mMax = M - 1
    var sequenceIdx = 0
    var currentIdx = startPoints[sequenceIdx]
    while (true) {


        rotated[n][m] = sequences[sequenceIdx][currentIdx]
        currentIdx = getNext(currentIdx, sequences[sequenceIdx].size)
        count++
        if (n == nMin && m != mMax) {
            m++
        } else if (n != nMax && m == mMax) {
            n++
        } else if (n == nMax && m != mMin) {
            m--
        } else if (n != nMin && m == mMin) {
            n--
        } else {
            throw IllegalArgumentException()
        }

        if (count == N * M) break

        if (n == nMin && m == mMin) {
            n++
            m++
            nMin++
            mMin++
            nMax--
            mMax--
            sequenceIdx++
            currentIdx = startPoints[sequenceIdx]
        }
    }

    rotated.forEach { println(it.joinToString(" ")) }
    br.close();
}

fun getNext(idx: Int, max: Int): Int {
    return if (idx + 1 == max) 0
    else idx + 1
}