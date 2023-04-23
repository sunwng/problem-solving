package prob_1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int M, N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] dp;

    static boolean isOut(int x, int y) {
        return x < 0 || x >= M || y < 0 || y >= N;
    }

    static int search(int x, int y) {
        if (dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 0;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (isOut(nx, ny) || map[nx][ny] <= map[x][y]) continue;
            dp[x][y] += search(nx, ny);
        }

        return dp[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        M = Integer.parseInt(given[0]);
        N = Integer.parseInt(given[1]);
        map = new int[M][N];
        dp = new int[M][N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        dp[0][0] = 1;

        for (int i = 0; i < M; i++) {
            given = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(given[j]);
            }
        }
        System.out.println(search(M - 1, N - 1));
    }
}
