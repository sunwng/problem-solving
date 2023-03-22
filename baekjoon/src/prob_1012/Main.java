package prob_1012;

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
    static int result;

    static void bfs(int[] start, boolean[][] visit) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx < 0 || nx >= X || ny < 0 || ny >= Y) continue;
                if (map[nx][ny] == 0 || visit[nx][ny]) continue;
                visit[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        result++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] given = br.readLine().split(" ");
            X = Integer.parseInt(given[0]);
            Y = Integer.parseInt(given[1]);
            map = new int[X][Y];
            int B = Integer.parseInt(given[2]);

            for (int b = 0; b < B; b++) {
                given = br.readLine().split(" ");
                map[Integer.parseInt(given[0])][Integer.parseInt(given[1])] = 1;
            }
            result = 0;
            boolean[][] visit = new boolean[X][Y];
            for (int x = 0; x < X; x++) {
                for (int y = 0; y < Y; y++) {
                    if ((map[x][y] == 0) || visit[x][y]) continue;
                    visit[x][y] = true;
                    bfs(new int[]{x, y}, visit);
                }
            }
            System.out.println(result);
        }

    }
}
