package prob_1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int limit = 100000;
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        if (K <= N) {
            System.out.println(Math.abs(K - N));
            return;
        }
        int[] dist = new int[limit + 1];
        Arrays.fill(dist, -1);
        dist[N] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == K) {
                System.out.println(dist[cur]);
                return;
            }

            int[] candSet = new int[]{cur - 1, cur + 1, cur * 2};
            for (int candI : candSet) {
                if (candI < 0 || candI > limit) continue;
                if (dist[candI] >= 0) continue;
                queue.add(candI);
                dist[candI] = dist[cur] + 1;
            }
        }

    }
}