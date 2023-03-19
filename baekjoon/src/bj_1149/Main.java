package bj_1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[][] rep = new int[totalNum + 1][3];
        int[][] dp = new int[totalNum + 1][3];

        for (int i = 1; i <= totalNum; i++) {
            String[] strArr = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                rep[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        for (int i = 1; i<= totalNum; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rep[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rep[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rep[i][2];
        }

        System.out.println(Math.min(Math.min(dp[totalNum][0], dp[totalNum][1]), dp[totalNum][2]));
    }
}

