package bj_9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());
        int[] dp;
        for (int j = 0; j < totalNum; j++) {
            int len = Integer.parseInt(br.readLine());
            dp = new int[len+1];
            dp[0] = 1;
            dp[1] = 1;
            if (len == 1) {
                System.out.println(1);
                continue;
            }
            dp[2] = 2;
            for (int i = 3; i <= len; i++) {
                dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }
            System.out.println(dp[len]);
        }
    }
}
