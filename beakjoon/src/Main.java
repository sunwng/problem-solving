import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] map;
    public static boolean flag;
    public static int N;
    public static int M;

    public static void dfs(boolean[] visit, int curNum, int targetNum) {
        if (curNum == targetNum) {
            flag = true;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (flag) return;
            if (map[curNum][i] == 0) continue;
            if (visit[i]) continue;
            visit[i] = true;
            dfs(visit, i, targetNum);
            visit[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int curNum = Integer.parseInt(temp[j]);
                if (curNum == 1) {
                    map[i][j + 1] = 1;
                    map[j + 1][i] = 1;
                }
            }
        }
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < M - 1; i++) {
            flag = false;
            boolean[] visit = new boolean[N + 1];
            visit[Integer.parseInt(temp[i])] = true;
            dfs(visit, Integer.parseInt(temp[i]), Integer.parseInt(temp[i + 1]));
            if (!flag) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }
}