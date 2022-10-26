package bj_1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] dp = new int[totalNum + 1];
        for (int i = 2; i <= totalNum; i++) {
            dp[i] = Integer.MAX_VALUE;
            if ((i % 2) == 0) {
                dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            }
            if ((i % 3) == 0) {
                dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
            }

            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }
        System.out.println(dp[totalNum]);


    }
}
