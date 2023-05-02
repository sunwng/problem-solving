package prob_1822;

import java.util.*;

class Solution {
    public int arraySign(int[] nums) {
        int signSum = 0;
        int answer = 1;
        for (int num : nums) {
            if (num < 0) signSum++;
            else if (num == 0) return 0;
        }

        return (signSum % 2 == 0) ? 1 : -1;
    }
}