package prob_879;

import java.util.*;

class Solution {
    int MOD = 1000000007;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int size = profit.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;

        for (int i = 0; i < size; i++) {
            int curG = group[i];
            int curP = profit[i];
            for (int j = minProfit; j >= 0; j--) {
                for (int k = n - curG; k >= 0; k--) {
                    int tempP = Math.min(curP + j, minProfit);
                    dp[k + curG][tempP] += dp[k][j];
                    dp[k + curG][tempP] %= MOD;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[i][minProfit]) % MOD;
        }
        return sum;
    }
}