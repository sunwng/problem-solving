package prob_1254;

import java.util.*;

class Solution {
    int X;
    int Y;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] map;
    boolean[][] visit;

    boolean checkRange(int x, int y) {
        return x < 0 || x >= X || y < 0 || y >= Y;
    }

    boolean isBoundary(int x, int y) {
        return x == 0 || x == X - 1 || y == 0 || y == Y - 1;
    }

    boolean search(int startX, int startY, boolean[][] visit) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY});
        boolean flag = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                if (checkRange(nextX, nextY)) continue;
                if (map[nextX][nextY] == 1 || visit[nextX][nextY]) continue;
                if (isBoundary(nextX, nextY)) flag = false;
                visit[nextX][nextY] = true;
                queue.add(new int[] {nextX, nextY});
            }
        }
        return flag;
    }

    public int closedIsland(int[][] grid) {
        X = grid.length;
        Y = grid[0].length;
        map = grid;
        visit = new boolean[X][Y];

        int answer = 0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (map[i][j] == 1 || visit[i][j] || isBoundary(i, j)) continue;
                visit[i][j] = true;
                if (search(i, j, visit)) answer++;
            }
        }

        return answer;
    }
}