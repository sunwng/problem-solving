package bj_7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int W;
    static int H;
    static int[][] map;
    static int[] dw = {1, -1, 0, 0};
    static int[] dh = {0, 0, 1, -1};

    public static int solution(ArrayList<Integer[]> initTomato) {
        int count = 0;
        boolean[][] visited = new boolean[H][W];
        Queue<ArrayList<Integer[]>> queue = new LinkedList<>();
        queue.add(initTomato);

        while (!queue.isEmpty()) {
            ArrayList<Integer[]> curSet = queue.poll();
            ArrayList<Integer[]> nextSet = new ArrayList<>();
            for (Integer[] tomatoI : curSet) {
                int curH = tomatoI[0];
                int curW = tomatoI[1];
                for (int i = 0; i < 4; i++) {
                    int nextH = curH + dh[i];
                    int nextW = curW + dw[i];
                    if (nextH < 0 || nextH >= H || nextW < 0 || nextW >= W) continue;
                    if (map[nextH][nextW] == -1 || visited[nextH][nextW]) continue;
                    nextSet.add(new Integer[]{nextH, nextW});
                    visited[nextH][nextW] = true;
                    map[nextH][nextW] = 1;
                }
                visited[curH][curW] = true;
            }
            if (!nextSet.isEmpty()) {
                queue.add(nextSet);
                count++;
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0) {
                    count = -1;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String size = br.readLine();
        String[] sizeArr = size.split(" ");
        W = Integer.parseInt(sizeArr[0]);
        H = Integer.parseInt(sizeArr[1]);
        map = new int[H][W];
        ArrayList<Integer[]> initTomato = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            String row = br.readLine();
            String[] rowArr = row.split(" ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(rowArr[j]);
                if (map[i][j] == 1) {
                    initTomato.add(new Integer[]{i, j});
                }
            }
        }
        if (initTomato.size() == (W * H)) {
            System.out.println(0);
        } else {
            int answer = solution(initTomato);
            System.out.println(answer);
        }
    }
}
