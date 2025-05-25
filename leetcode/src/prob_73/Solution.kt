package prob_73

class Solution {
    private val dirM = arrayOf(-1, 1, 0, 0)
    private val dirN = arrayOf(0, 0, -1, 1)

    private fun isOut(m: Int, n: Int, M: Int, N: Int) = m < 0 || m >= M || n < 0 || n >= N

    fun setZeroes(matrix: Array<IntArray>) {
        val zeroPositions = mutableListOf<Pair<Int, Int>>()

        val M = matrix.size
        val N = matrix[0].size

        for (m in 0 until M) {
            for (n in 0 until N) {
                if (matrix[m][n] != 0) continue

                zeroPositions.add(m to n)
            }
        }

        zeroPositions.forEach {
            for (i in 0..3) {
                var nextM = it.first + dirM[i]
                var nextN = it.second + dirN[i]

                while (isOut(nextM, nextN, M, N).not()) {
                    matrix[nextM][nextN] = 0
                    nextM += dirM[i]
                    nextN += dirN[i]
                }
            }
        }
    }
}