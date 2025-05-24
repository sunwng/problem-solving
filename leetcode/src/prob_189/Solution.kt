package prob_189

class Solution {
    fun rotate(nums: IntArray, k: Int) {
        val size = nums.size
        val remainder = k % size

        val original = nums.copyOf()

        for (i in 0 until size) {
            nums[(remainder + i) % size] = original[i]
        }

        val temp = mutableListOf<Char>()

        temp.reversed()
    }
}