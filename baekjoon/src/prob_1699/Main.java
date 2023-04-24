package prob_1699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int root = (int) Math.sqrt(i);
            if (root == (double) i / root) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + 1;
                for (int j = root; j >= 2; j--) {
                    dp[i] = Math.min(dp[i], dp[j * j] + dp[i - (j * j)]);
                }
            }
        }

        System.out.println(dp[N]);
    }
}