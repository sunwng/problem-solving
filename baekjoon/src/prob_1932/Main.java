package prob_1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[][] dp = new int[totalNum + 1][totalNum];

        for (int i = 0; i < totalNum; i++) {
            String[] strArr = br.readLine().split(" ");
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i + 1][j] = dp[i][j] + Integer.parseInt(strArr[j]);
                } else if (j == i) {
                    dp[i + 1][j] = dp[i][j - 1] + Integer.parseInt(strArr[j]);
                } else {
                    dp[i + 1][j] = Math.max(dp[i][j - 1], dp[i][j]) + Integer.parseInt(strArr[j]);
                }
            }
        }
        int[] result = dp[totalNum];
        Arrays.sort(result);
        System.out.println(result[totalNum - 1]);
    }
}
