package prob_2466;

import java.util.*;

class Solution {

    int MOD = 1_000_000_007;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int[][] dp = new int[high + 1][2];
        dp[zero][0] = 1;
        dp[one][1] = 1;

        for (int i = 1; i <= high; i++) {
            if (i != zero) {
                int zeroIdx = Math.max((i - zero), 0);
                dp[i][0] = (dp[zeroIdx][0] + dp[zeroIdx][1]) % MOD;
            }
            if (i != one) {
                int oneIdx = Math.max((i - one), 0);
                dp[i][1] = (dp[oneIdx][0] + dp[oneIdx][1]) % MOD;
            }
        }

        int answer = 0;
        for (int i = low; i <= high; i++) {
            answer = (answer + dp[i][0]) % MOD;
            answer = (answer + dp[i][1]) % MOD;
        }
        return answer;
    }
}
