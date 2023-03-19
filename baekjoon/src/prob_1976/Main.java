package prob_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[][] map;
    public static boolean flag;
    public static int N;
    public static int M;

    public static int[] parent;

    public static int find(int numIn) {
        while (numIn != parent[numIn]) {
            numIn = parent[numIn];
        }
        return numIn;
    }

    public static void apply(int i, int j) {
        int parentI = find(i);
        int parentJ = find(j);
        if (parentI != parentJ) {
            parent[parentJ] = parentI;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                int curNum = Integer.parseInt(temp[j-1]);
                if (curNum == 1) {
                    if (i < j) {
                        apply(i, j);
                    } else {
                        apply(j, i);
                    }
                }
            }
        }

        String[] temp = br.readLine().split(" ");
        int prev = Integer.parseInt(temp[0]);
        for (int i = 1; i < M; i++) {
            int cur = Integer.parseInt(temp[i]);
            if (find(prev) != find(cur)) {
                System.out.println("NO");
                return;
            }
            prev = cur;
        }
        System.out.println("YES");
    }
}