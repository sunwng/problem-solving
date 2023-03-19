package prob_1904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        long[] dp = new long[totalNum + 1];
        if (totalNum <= 2) {
            System.out.println(totalNum);
            return;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= totalNum; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }
        System.out.println(dp[totalNum]);
    }
}
