package prob_11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] rep = new int[totalNum];
        int[] dp = new int[totalNum];
        String[] temp = br.readLine().split(" ");

        for (int i = 0; i < totalNum; i++) {
            rep[i] = Integer.parseInt(temp[i]);
        }

        for (int i = 0; i < totalNum; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (rep[i] > rep[j] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        Arrays.sort(dp);
        System.out.println(dp[totalNum - 1]);
    }
}
