package prob_1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int target;
    public static int[] map;
    public static int count;
    public static int N;

    public static void find(int sum, int depth) {
        if (depth == N) {
            if (sum == target) count++;
            return;
        }
        find(sum + map[depth], depth + 1);
        find(sum, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        target = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(temp[i]);
        }
        count = 0;
        find(0, 0);
        if (target == 0) count--;
        System.out.println(count);
    }
}