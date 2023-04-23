package prob_1416;

import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfArrays(String s, int k) {
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;

        for (int i = N - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;

            int start = i;
            int end = i;
            int sum = 0;
            while (end < N) {
                if (end - start + 1 > Integer.toString(k).length()) break;
                else if (Long.parseLong(s.substring(start, end + 1)) > Integer.MAX_VALUE) break;

                int temp = Integer.parseInt(s.substring(start, end + 1));
                if (temp > k) break;
                sum = (sum + dp[end + 1]) % MOD;
                end++;
            }
            dp[i] = sum;
        }



        return dp[0];
    }
}