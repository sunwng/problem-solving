package prob_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] dn = {-1, 1, 0, 0};
    static int[] dm = {0, 0, -1, 1};
    static int[][] distS;
    static int[][] distE;
    static int result;
    static int[][] map;

    public static boolean boundCheck(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        result = Integer.MAX_VALUE;
        map = new int[N][M];
        distS = new int[N][M];
        distE = new int[N][M];
        for (int i = 0; i < N; i++) {
            String tempStr = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tempStr.charAt(j) - '0';
                distS[i][j] = -1;
                distE[i][j] = -1;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        distS[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nn = cur[0] + dn[i];
                int nm = cur[1] + dm[i];
                if (boundCheck(nn, nm) || map[nn][nm] == 1 || distS[nn][nm] != -1) continue;
                distS[nn][nm] = distS[cur[0]][cur[1]] + 1;
                queue.add(new int[]{nn, nm});
            }
        }
        queue.add(new int[]{N - 1, M - 1});
        distE[N - 1][M - 1] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nn = cur[0] + dn[i];
                int nm = cur[1] + dm[i];
                if (boundCheck(nn, nm) || map[nn][nm] == 1 || distE[nn][nm] != -1) continue;
                distE[nn][nm] = distE[cur[0]][cur[1]] + 1;
                queue.add(new int[]{nn, nm});
            }
        }

        if (distS[N - 1][M - 1] != -1) result = distS[N - 1][M - 1];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                int distSMin = Integer.MAX_VALUE;
                int distEMin = Integer.MAX_VALUE;
                for (int k = 0; k < 4; k++) {
                    int nn = i + dn[k];
                    int nm = j + dm[k];
                    if (boundCheck(nn, nm)) continue;
                    if (distS[nn][nm] != -1) distSMin = Math.min(distSMin, distS[nn][nm]);
                    if (distE[nn][nm] != -1) distEMin = Math.min(distEMin, distE[nn][nm]);
                }
                if (distSMin != Integer.MAX_VALUE && distEMin != Integer.MAX_VALUE) {
                    result = Math.min(result, distSMin + distEMin + 1);
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}