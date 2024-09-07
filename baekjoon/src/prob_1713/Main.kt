package prob_1713

fun main() {
    val br = System.`in`.bufferedReader()

    val N_PICTURES = br.readLine().toInt()
    val N_STUDENTS = br.readLine().toInt()

    val GIVEN_VOTES = br.readLine().split(' ').map { it.toInt() }

    val students = Array(N_STUDENTS + 1) {
        Student(
            studentId = it,
            voteCount = 0,
            insertedAt = 0,
            isPosted = false,
        )
    }

    val pictures = mutableListOf<Student>()

    GIVEN_VOTES.forEachIndexed() { time, vote ->
        if (students[vote].isPosted) {
            students[vote].voted()
        } else {
            if (pictures.size == N_PICTURES) {
                pictures.sortWith(
                    compareBy<Student> { it.voteCount }
                        .thenBy{ it.insertedAt }
                )
                pictures.first().removed()
                pictures.removeFirst()
            }
            students[vote].inserted(time)
            pictures.add(students[vote])
        }
    }

    pictures.sortBy { it.studentId }

    println(pictures.map { it.studentId }.joinToString(" "))

    br.close()
}

data class Student(
    val studentId: Int,
    var voteCount: Int,
    var insertedAt: Int,
    var isPosted: Boolean,
) {
    fun voted() {
        this.voteCount++
    }

    fun inserted(insertedAt: Int) {
        this.voteCount = 1
        this.insertedAt = insertedAt
        this.isPosted = true
    }

    fun removed() {
        this.voteCount = 0
        this.insertedAt = 0
        this.isPosted = false
    }
}