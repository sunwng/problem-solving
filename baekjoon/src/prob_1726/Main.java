package prob_1726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    static boolean isOut(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    static void bfs(int x, int y, int dir, int targetX, int targetY, int targetDir) { // 1 동 2 서 3 남 4 북
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] o1, int[] o2) -> o1[3] - o2[3]);
        queue.add(new int[] {x, y, dir, 0});

        int[][][] cost = new int[5][N][M];
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        cost[dir][x][y] = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == targetX && now[1] == targetY && now[2] == targetDir) {
                answer = Math.min(answer, now[3]);
                continue;
            }
            int opposite = 0;
            if (now[2] % 2 == 1) opposite = now[2] + 1;
            else opposite = now[2] - 1;

            if (cost[opposite][now[0]][now[1]] > now[3] + 2) {
                queue.add(new int[] {now[0], now[1], opposite, now[3] + 2});
                cost[opposite][now[0]][now[1]] = now[3] + 2;
            }
            for (int i = 1; i <= 4; i++) {
                if (i == now[2] || i == opposite) continue;
                if (cost[i][now[0]][now[1]] <= now[3] + 1) continue;
                queue.add(new int[] {now[0], now[1], i, now[3] + 1});
                cost[i][now[0]][now[1]] = now[3] + 1;
            }
            int nx = now[0];
            int ny = now[1];
            for (int i = 0; i < 3; i++) {
                nx += dx[now[2]];
                ny += dy[now[2]];
                if (isOut(nx, ny) || cost[now[2]][nx][ny] <= now[3] + 1) continue;
                if (map[nx][ny] == 1) break;
                cost[now[2]][nx][ny] = now[3] + 1;
                queue.add(new int[] {nx, ny, now[2], now[3] + 1});
            }
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
        given = br.readLine().split(" ");
        int startX = Integer.parseInt(given[0]) - 1;
        int startY = Integer.parseInt(given[1]) - 1;
        int startDir = Integer.parseInt(given[2]);
        given = br.readLine().split(" ");
        int targetX = Integer.parseInt(given[0]) - 1;
        int targetY = Integer.parseInt(given[1]) - 1;
        int targetDir = Integer.parseInt(given[2]);
        bfs(startX, startY, startDir, targetX, targetY, targetDir);
        System.out.println(answer);

    }
}