package bj_2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] rep = new int[501];
        int[] dp = new int[501];

        for (int i = 0; i < totalNum; i++) {
            String[] temp = br.readLine().split(" ");
            rep[Integer.parseInt(temp[0])] = Integer.parseInt(temp[1]);
        }

        for (int i = 1; i <= 500; i++) {
            if (rep[i] == 0) continue;
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (rep[i] > rep[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        Arrays.sort(dp);
        System.out.println(totalNum - dp[500]);
    }
}
