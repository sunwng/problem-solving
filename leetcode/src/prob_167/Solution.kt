package prob_167

class Solution {

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.size - 1

        val result = IntArray(2)

        while (left < right) {
            val sum = numbers[left] + numbers[right]
            if (sum == target) {
                result[0] = left + 1
                result[1] = right + 1
                break
            } else if (sum > target) {
                right--
            } else {
                left++
            }
        }

        return result
    }
}