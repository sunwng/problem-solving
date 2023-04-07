package prob_1020;

import java.util.*;

class Solution {

    boolean[][] visit;
    int M;
    int N;
    int answer = 0;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    boolean isOut(int x, int y) {
        return x < 0 || x == M || y < 0 || y == N;
    }

    boolean isBoundary(int x, int y) {
        return x == 0 || x == M - 1 || y == 0 || y == N - 1;
    }

    void search(int x, int y, int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        boolean flag = true;
        int count = 1;

        while (true) {
            int[] now = queue.poll();

            if(isBoundary(now[0], now[1])) flag = false;

            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];
                if (isOut(nextX, nextY)) continue;
                if (visit[nextX][nextY] || grid[nextX][nextY] == 0) continue;
                visit[nextX][nextY] = true;
                count++;
                queue.add(new int[] {nextX, nextY});
            }

            if (queue.isEmpty()) {
                if (flag) answer += count;
                break;
            }
        }

    }

    public int numEnclaves(int[][] grid) {
        M = grid.length;
        N = grid[0].length;
        visit = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] || grid[i][j] == 0) continue;
                visit[i][j] = true;
                search(i, j, grid);
            }
        }
        return answer;
    }
}