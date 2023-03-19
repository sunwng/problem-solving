package prob_1926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int X;
    public static int Y;
    public static int[][] map;
    public static int count;
    public static int max;

    public static void bfs() {
        boolean[][] visit = new boolean[X][Y];

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (visit[i][j]) continue;
                visit[i][j] = true;
                if (map[i][j] == 0) continue;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j});
                int localCount = 0;
                while (!queue.isEmpty()) {
                    localCount++;
                    int[] cur = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = cur[0] + dx[k];
                        int ny = cur[1] + dy[k];
                        if (nx < 0 || nx >= X || ny < 0 || ny >= Y) continue;
                        if (visit[nx][ny]) continue;
                        visit[nx][ny] = true;
                        if (map[nx][ny] == 0) continue;
                        queue.add(new int[]{nx, ny});
                    }
                }
                count++;
                max = Math.max(localCount, max);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizeArr = br.readLine().split(" ");
        X = Integer.parseInt(sizeArr[0]);
        Y = Integer.parseInt(sizeArr[1]);

        map = new int[X][Y];
        for (int i = 0; i < X; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < Y; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        count = 0;
        max = 0;

        bfs();

        System.out.println(count);
        System.out.println(max);

    }
}