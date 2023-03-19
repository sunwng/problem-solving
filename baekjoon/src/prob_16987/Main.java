package prob_16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N;
    public static int[][] info;
    public static int result;
    public static int count;

    public static void find(int depth) {
        if (depth == N) {
            result = Math.max(result, count);
            return;
        }

        if (info[depth][0] <= 0 || count == N - 1) {
            find(depth + 1);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i == depth || info[i][0] <= 0) continue;

            info[depth][0] -= info[i][1];
            info[i][0] -= info[depth][1];
            if (info[depth][0] <= 0) count++;
            if (info[i][0] <= 0) count++;

            find(depth + 1);

            if (info[depth][0] <= 0) count--;
            if (info[i][0] <= 0) count--;
            info[depth][0] += info[i][1];
            info[i][0] += info[depth][1];
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            info[i][0] = Integer.parseInt(temp[0]);
            info[i][1] = Integer.parseInt(temp[1]);
        }
        result = 0;
        count = 0;
        find(0);
        System.out.println(result);
    }
}