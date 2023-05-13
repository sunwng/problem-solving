package prob_2140;

import java.util.*;

class Solution {
    public long mostPoints(int[][] questions) {
        int N = questions.length;
        long[] dp = new long[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];
            int before = Math.min(i + questions[i][1] + 1, N);
            dp[i] = Math.max(dp[i], dp[before] + questions[i][0]);
        }
        return dp[0];
    }
}