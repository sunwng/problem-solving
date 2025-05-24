package prob_1663

class Solution {
    private val alphabets = "abcdefghijklmnopqrstuvwxyz"

    fun getSmallestString(n: Int, k: Int): String {
        val smallestString = mutableListOf<Char>()

        var remainder = k

        for (i in n downTo 1) {
            if ((remainder / alphabets.length) > 0) {
                if ((remainder - alphabets.length) < (i - 1)) {
                    val toAdd = alphabets[remainder - i]
                    smallestString.add(toAdd)
                    remainder -= toAdd.toNumericValue()
                } else {
                    smallestString.add(alphabets.last())
                    remainder -= alphabets.last().toNumericValue()
                }
            } else {
                val toAdd = alphabets[remainder - i]
                smallestString.add(toAdd)
                remainder -= toAdd.toNumericValue()
            }
        }


        return smallestString.reversed().joinToString("")
    }

    private fun Char.toNumericValue() = this.lowercaseChar() - 'a' + 1
}