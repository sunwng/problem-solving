package prob_16981

fun main() {
    val br = System.`in`.bufferedReader()

    val input = br.readLine().split(' ').map { it.toInt() }

    val R = input[0]
    val C = input[1]
    val N = input[2]

    val map = Array(R) { IntArray(C) }

    for (r in 0..<R) {
        val current = br.readLine()

        current.forEachIndexed { idx, c ->
            if (c == 'O') {
                map[r][idx] = 1
            } else {
                map[r][idx] = -1
            }
        }
    }

    var time = 2

    while (time <= N) {

        for (r in 0..<R) {
            for (c in 0..<C) {
                if (map[r][c] == -1) continue
                map[r][c]++
            }
        }


        if (time % 2 == 0) {
            for (r in 0..<R) {
                for (c in 0..<C) {
                    if (map[r][c] != -1) continue
                    map[r][c]++
                }
            }
        } else {
            for (r in 0..<R) {
                for (c in 0..<C) {
                    if (map[r][c] != 3) continue
                    removeAround(r, c, map, R, C)
                    map[r][c] = -1
                }
            }
        }
        time++
    }

    val sb = StringBuilder()
    for (r in 0..<R) {
        for (c in 0..<C) {
            if (map[r][c] == -1) {
                sb.append(".")
            } else {
                sb.append("O")
            }
        }
        if (r < R - 1) {
            sb.append("\n")
        }
    }

    println(sb.toString())

    br.close()

}

fun removeAround(r: Int, c: Int, map: Array<IntArray>, R: Int, C: Int) {
    val dr = arrayOf(-1, 1, 0, 0)
    val dc = arrayOf(0, 0, -1, 1)

    for (idx in 0..3) {
        val targetR = r + dr[idx]
        val targetC = c + dc[idx]
        if (targetR < 0 || targetR >= R || targetC < 0 || targetC >= C) continue
        if (map[targetR][targetC] == 3) continue
        map[targetR][targetC] = -1
    }
}