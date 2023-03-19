package bj_4179;

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
    public static int[][] distJ;
    public static int[][] distF;

    public static int bfs(Queue<int[]> queueJ, Queue<int[]> queueF) {

        int result = -1;

        while (!queueJ.isEmpty()) {
            int numF = queueF.size();
            for (int i = 0; i < numF; i++) {
                int[] curF = queueF.poll();
                for (int j = 0; j < 4; j++) {
                    int nFX = curF[0] + dx[j];
                    int nFY = curF[1] + dy[j];
                    if (nFX < 0 || nFX >= X || nFY < 0 || nFY >= Y) continue;
                    if (map[nFX][nFY] == 1) continue;
                    map[nFX][nFY] = 1;
                    queueF.add(new int[]{nFX, nFY});
                }
            }
            int numJ = queueJ.size();
            for (int i = 0; i < numJ; i++) {
                int[] curJ = queueJ.poll();

                if (curJ[0] == 0 || curJ[0] == X - 1 || curJ[1] == 0 || curJ[1] == Y - 1) {
                    result = distJ[curJ[0]][curJ[1]];
                    return result;
                }

                for (int j = 0; j < 4; j++) {
                    int nJX = curJ[0] + dx[j];
                    int nJY = curJ[1] + dy[j];
                    if (nJX < 0 || nJX >= X || nJY < 0 || nJY >= Y) continue;
                    if (distJ[nJX][nJY] >= 0 || map[nJX][nJY] == 1) continue;
                    if (distF[nJX][nJY] != -1 && distF[nJX][nJY] <= distJ[curJ[0]][curJ[1]]) continue;
                    distJ[nJX][nJY] = distJ[curJ[0]][curJ[1]] + 1;
                    queueJ.add(new int[]{nJX, nJY});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizeArr = br.readLine().split(" ");
        X = Integer.parseInt(sizeArr[0]);
        Y = Integer.parseInt(sizeArr[1]);

        map = new int[X][Y];
        distJ = new int[X][Y];
        distF = new int[X][Y];
        int[] J = new int[2];
        int[] F = new int[2];

        Queue<int[]> queueJ = new LinkedList<>();
        Queue<int[]> queueF = new LinkedList<>();

        for (int i = 0; i < X; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < Y; j++) {
                distJ[i][j] = -1;
                distF[i][j] = -1;
                if (temp[j].equals("#")) {
                    map[i][j] = 1;
                } else if (temp[j].equals("J")) {
                    queueJ.add(new int[]{i, j});
                    distJ[i][j] = 0;
                } else if (temp[j].equals("F")) {
                    queueF.add(new int[]{i, j});
                    distF[i][j] = 0;
                }
            }
        }

        int count = bfs(queueJ, queueF);

        if (count == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(count + 1);
        }
    }
}