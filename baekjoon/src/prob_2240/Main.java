package prob_2240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, W;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        T = Integer.parseInt(given[0]);
        W = Integer.parseInt(given[1]);
        nums = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[T + 1][W + 1][3];

        for (int i = 1; i <= T; i++) {
            dp[i][0][1] = dp[i - 1][0][1] + (nums[i] == 1 ? 1 : 0);

            for (int j = 1; j <= W; j++) {
                if (j > i) break;
                if (j % 2 == 0) {
                    dp[i][j][1] = Math.max(dp[i - 1][j - 1][2], dp[i - 1][j][1]) + (nums[i] == 1 ? 1 : 0);
                } else {
                    dp[i][j][2] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j][2]) + (nums[i] == 2 ? 1 : 0);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, dp[T][i][1]);
            answer = Math.max(answer, dp[T][i][2]);
        }
        System.out.println(answer);
    }
}