package prob_10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        long[][] dp = new long[totalNum + 1][10];
        long num = 1000000000;
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= totalNum; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] % num;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % num;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % num;
                }
            }
        }
        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[totalNum][i];
        }

        System.out.println(answer % num);
    }
}
