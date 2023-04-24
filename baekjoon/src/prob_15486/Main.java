package prob_15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] plans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        plans = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] given = br.readLine().split(" ");
            plans[i][0] = Integer.parseInt(given[0]);
            plans[i][1] = Integer.parseInt(given[1]);
        }

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i]);
            if (i + plans[i - 1][0] - 1 > N) continue;
            dp[i + plans[i - 1][0] - 1] = Math.max(dp[i + plans[i - 1][0] - 1], dp[i - 1] + plans[i - 1][1]);
        }
        System.out.println(dp[N]);
    }
}