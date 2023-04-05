package prob_2439;

class Solution {
    public int minimizeArrayValue(int[] nums) {
        int max = 0;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, (int) Math.ceil(sum / (double) (i + 1)));
        }
        return max;
    }
}