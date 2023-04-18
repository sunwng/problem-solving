package prob_1823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = nums[i] * N;
        }

        for (int size = 1; size < N; size++) {
            for (int start = 0; start + size < N; start++) {
                int left = dp[start + 1][start + size] + nums[start] * (N - size);
                int right = dp[start][start + size - 1] + nums[start + size] * (N - size);
                dp[start][start + size] = Math.max(left, right);
            }
        }
        System.out.println(dp[0][N - 1]);
    }
}
