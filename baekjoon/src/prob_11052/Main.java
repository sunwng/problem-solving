package prob_11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] packs = new int[N + 1];
        String[] given = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            packs[i] = Integer.parseInt(given[i - 1]);
        }
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + packs[j]);
            }
            dp[i] = Math.max(dp[i], packs[i]);
        }
        System.out.println(dp[N]);
    }
}