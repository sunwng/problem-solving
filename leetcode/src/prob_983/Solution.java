package prob_983;

import java.util.*;

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        dp[0] = 0;
        int idx = 0;
        for (int i = 1; i <= lastDay; i++) {
            if (i == days[idx]) {
                int oneDay = dp[i - 1] + costs[0];
                int sevenDay = dp[Math.max(0, i - 7)] + costs[1];
                int thirtyDay = dp[Math.max(0, i - 30)] + costs[2];
                dp[i] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
                idx++;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[lastDay];
    }
}