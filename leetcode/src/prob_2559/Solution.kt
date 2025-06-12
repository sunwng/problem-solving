package prob_2559

class Solution {
    private val vowels = setOf('a', 'e', 'i', 'o', 'u')

    fun vowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {
        val cumulativeSum = IntArray(words.size + 2) { 0 }

        for (i in words.indices) {
            cumulativeSum[i + 1] = cumulativeSum[i]

            if (vowels.contains(words[i].first()) && vowels.contains(words[i].last())) {
                cumulativeSum[i + 1]++
            }
        }

        val result = queries.map { query ->
            cumulativeSum[query[1] + 1] - cumulativeSum[query[0]]
        }

        return result.toIntArray()
    }
}