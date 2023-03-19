package prob_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N;
    public static int count = 0;

    public static boolean check(int depth, int[] map) {
        for (int i = 0; i < depth; i++) {
            if (map[i] == map[depth]) {
                return false;
            } else if (Math.abs(depth - i) == Math.abs(map[depth] - map[i])) {
                return false;
            }
        }
        return true;
    }
    public static void dfs(int depth, int[] map) {
        if (depth == N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            map[depth] = i;
            if (check(depth, map)) {
                dfs(depth+1, map);
            }
        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfs(0, new int[N+1]);
        System.out.println(count);

    }
}