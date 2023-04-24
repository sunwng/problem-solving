package prob_2218;

import java.util.*;

class Solution {

    int N;
    int[][] dp;

    int search(int idx, int k, List<List<Integer>> piles) {
        if (idx == piles.size() || k == 0) return 0;
        if (dp[idx][k] > 0) return dp[idx][k];

        int sum = 0;
        int max = search(idx + 1, k, piles);
        for (int i = 0; i < piles.get(idx).size() && k - i - 1 >= 0; i++) {
            sum += piles.get(idx).get(i);
            max = Math.max(max, search(idx + 1, k - i - 1, piles) + sum);
        }
        dp[idx][k] = max;
        return dp[idx][k];
    }

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        N = piles.size();
        dp = new int[N][k + 1];
        return search(0, k, piles);
    }
}