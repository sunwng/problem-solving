package prob_2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int X;
    static int Y;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;

    static boolean boundCheck(int x, int y) {
        return x < 0 || x >= X || y < 0 || y >= Y;
    }

    static boolean waterCheck(int x, int y) {
        return map[x][y] == -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");
        X = Integer.parseInt(nums[0]);
        Y = Integer.parseInt(nums[1]);
        map = new int[X][Y];
        for (int i = 0; i < X; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < Y; j++) {
                if (line[j].equals("W")) map[i][j] = -1;
                else map[i][j] = 0;
            }
        }

        int result = 0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (waterCheck(i, j)) continue;
                boolean[][] visit = new boolean[X][Y];
                visit[i][j]= true;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j, 0});
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    result = Math.max(result, cur[2]);
                    for (int k = 0; k < 4; k++) {
                        int nx = cur[0] + dx[k];
                        int ny = cur[1] + dy[k];
                        if (boundCheck(nx, ny)) continue;
                        if (waterCheck(nx, ny)) continue;
                        if (visit[nx][ny]) continue;
                        visit[nx][ny] = true;
                        queue.add(new int[]{nx, ny, cur[2] + 1});
                    }
                }
            }
        }
        System.out.println(result);

    }
}