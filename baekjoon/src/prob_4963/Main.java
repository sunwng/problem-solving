package prob_4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int count;
    static int W;
    static int H;
    static int[] dw = {0, -1, 1};
    static int[] dh = {0, -1, 1};
    public static boolean[][] bfs(int h, int w, int[][] map, boolean[][] visited) {
        Queue<Integer> queue = new LinkedList<>();
        count++;
        queue.add(h);
        queue.add(w);
        while (!queue.isEmpty()) {
            int curH = queue.poll();
            int curW = queue.poll();
//            visited[curH][curW] = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int nextH = curH + dh[i];
                    int nextW = curW + dw[j];
                    if (nextH < 0 || nextH >= H || nextW < 0 || nextW >= W) continue;
                    if (visited[nextH][nextW] || map[nextH][nextW] == 0) continue;
                    queue.add(nextH);
                    queue.add(nextW);
                    visited[curH][curW] = true;
                    visited[nextH][nextW] = true;
                }
            }
        }

        return visited;
    }
    public static int solution(int w, int h, int[][] map) {
        count = 0;
        W = w;
        H = h;
        boolean[][] visited = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (visited[i][j] || map[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }
                visited = bfs(i, j, map, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String size = br.readLine();
            if (size.equals("0 0")) break;
            String[] sizeArr = size.split(" ");
            int w = Integer.parseInt(sizeArr[0]);
            int h = Integer.parseInt(sizeArr[1]);
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                String row = br.readLine();
                String[] rowArr = row.split(" ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(rowArr[j]);
                }
            }
            int answer = solution(w, h, map);
            System.out.println(answer);
        }
    }
}

