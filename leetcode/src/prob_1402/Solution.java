package prob_1402;

import java.util.*;

class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        int N = satisfaction.length;
        Arrays.sort(satisfaction);
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[j][i] = dp[j][i - 1] + satisfaction[i - 1] * (i - j + 1);
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i][N]);
        }
        return result;
    }
}
