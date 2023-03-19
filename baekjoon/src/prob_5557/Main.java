package prob_5557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        String temp = br.readLine();
        int[] numArr = new int[totalNum];
        long[][] dp = new long[totalNum][21];

        String[] tempArr = temp.split(" ");
        for (int i = 0; i < totalNum; i++) {
            numArr[i] = Integer.parseInt(tempArr[i]);
        }
        dp[0][numArr[0]] = 1;

        for (int i = 1; i < totalNum - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] != 0) {
                    int plus = j + numArr[i];
                    if (plus >= 0 && plus <= 20) dp[i][plus] += dp[i - 1][j];
                    int minus = j - numArr[i];
                    if (minus >= 0 && minus <= 20) dp[i][minus] += dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[totalNum - 2][numArr[totalNum - 1]]);
    }
}
