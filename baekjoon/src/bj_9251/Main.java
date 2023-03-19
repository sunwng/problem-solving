package bj_9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firStr = br.readLine();
        String[] firArr = firStr.split("");
        int firLen = firStr.length();
        String secStr = br.readLine();
        String[] secArr = secStr.split("");
        int secLen = secStr.length();
        int[][] dp = new int[secLen+1][firLen+1];

        for (int i = 1; i <= firLen; i++) {
            for (int j = 1; j <= secLen; j++) {
                if (secArr[j-1].equals(firArr[i-1])) {
                    dp[j][i] = dp[j-1][i-1] + 1;
                } else {
                    dp[j][i] = Math.max(dp[j-1][i], dp[j][i-1]);
                }
            }
        }
        System.out.println(dp[secLen][firLen]);
    }
}
