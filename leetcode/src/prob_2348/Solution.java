package prob_2348;

import java.util.*;

class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int N = nums.length;
        long counter = 0;
        long result = 0;

        for (int num : nums) {
            if (num == 0) {
                result += (++counter);
            } else {
                counter = 0;
            }
        }
        return result;
    }
}