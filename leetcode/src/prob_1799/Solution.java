package prob_1799;

import java.util.*;

class Solution {
    public int maxScore(int[] nums) {
        int N = nums.length;

        Map<Integer, Integer> gcds = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                gcds.put(((1 << i) | (1 << j)), getGCD(nums[i], nums[j]));
            }
        }

        int[] dp = new int[1 << N];

        for (int i = 0; i < (1 << N); i++) {
            int count = Integer.bitCount(i);
            if ((count % 2) != 0) continue;
            for (int pair : gcds.keySet()) {
                if ((i & pair) != 0) continue;
                dp[i | pair] = Math.max(dp[i | pair], dp[i] + gcds.get(pair) * (count / 2 + 1));
            }
        }
        return dp[(1 << N) - 1];
    }

    int getGCD(int x, int y) {
        return y == 0 ? x : getGCD(y, x % y);
    }

}
