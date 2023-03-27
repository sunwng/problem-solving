package prob_64;

import java.util.*;

class Solution {

    public int minPathSum(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        int[][] dp = new int[M][N];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) continue;
                int left, up;
                if (i == 0) up = Integer.MAX_VALUE;
                else up = dp[i - 1][j];
                if (j == 0) left = Integer.MAX_VALUE;
                else left = dp[i][j - 1];
                dp[i][j] = Math.min(left, up) + grid[i][j];
            }
        }

        return dp[M - 1][N - 1];
    }
}