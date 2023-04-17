package prob_17484;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int answer = Integer.MAX_VALUE;
    static int[] dy = {-1, 0, 1};
    static int[][] map;

    static boolean isOut(int y) {
        return y < 0 || y >= M;
    }

    static void search(int x, int y, int cost, int prev) {
        if (x == N - 1) {
            answer = Math.min(answer, cost);
            return;
        }
        for (int dir = 0; dir < 3; dir++) {
            if (dy[dir] == prev) continue;
            int ny = y + dy[dir];
            if (isOut(ny)) continue;
            search(x + 1, ny, cost + map[x + 1][ny], dy[dir]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        M = Integer.parseInt(given[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            given = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(given[j]);
            }
        }
        for (int i = 0; i < M; i++) {
            search(0, i, map[0][i], -2);
        }
        System.out.println(answer);
    }
}