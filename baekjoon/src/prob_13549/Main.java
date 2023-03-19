package prob_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        if (K < N) {
            System.out.println(N - K);
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        boolean[] visit = new boolean[100001];
        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == K) answer = Math.min(answer, cur[1]);
            if (visit[cur[0]]) continue;
            visit[cur[0]] = true;
            if (cur[0] * 2 <= 100000) {
                queue.add(new int[]{cur[0] * 2, cur[1]});
            }
            if (cur[0] - 1 >= 0) {
                queue.add(new int[]{cur[0] - 1, cur[1] + 1});
            }
            if (cur[0] + 1 <= 100000) {
                queue.add(new int[]{cur[0] + 1, cur[1] + 1});
            }
        }
        System.out.println(answer);
    }
}