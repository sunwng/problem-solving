package bj_2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] rep = new int[totalNum + 1];
        int[] dp = new int[totalNum + 1];
        for (int i = 1; i <= totalNum; i++) {
            rep[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= totalNum; i++) {
            if (i == 1) {
                dp[i] = rep[i];
            } else if (i == 2) {
                dp[i] = rep[i] + rep[i - 1];
            } else {
                dp[i] = Math.max(dp[i - 3] + rep[i - 1], dp[i - 2]) + rep[i];
            }
        }
        System.out.println(dp[totalNum]);
    }
}
