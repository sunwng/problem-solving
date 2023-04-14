package prob_11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        int N = Integer.parseInt(given[0]);
        int M = Integer.parseInt(given[1]);
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            given = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(given[j]);
            }
        }

        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i - 1][j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            given = br.readLine().split(" ");
            int r1 = Integer.parseInt(given[0]);
            int c1 = Integer.parseInt(given[1]);
            int r2 = Integer.parseInt(given[2]);
            int c2 = Integer.parseInt(given[3]);
            System.out.println(dp[r2][c2] + dp[r1 - 1][c1 - 1] - dp[r1 - 1][c2] - dp[r2][c1 - 1]);
        }

    }
}