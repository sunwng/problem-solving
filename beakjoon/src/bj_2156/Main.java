package bj_2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] dp = new int[totalNum+1];
        int[] rep = new int[totalNum+1];
        for (int i = 1; i <= totalNum; i++) {
            rep[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = rep[1];
        for (int i = 2; i <= totalNum; i++) {
            if (i == 2) {
                dp[2] = dp[1] + rep[2];
                continue;
            }
            dp[i] = Math.max(dp[i-3]+rep[i-1]+rep[i], Math.max(dp[i-2]+rep[i], dp[i-1]));
        }

        System.out.println(dp[totalNum]);


    }
}
