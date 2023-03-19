package prob_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int E;
    static int N;
    static int[] result;
    static ArrayList<int[]>[] info;

    public static void dijkstra(int start) {
        result = new int[N + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        queue.add(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] != result[cur[0]]) continue;
            for (int[] next : info[cur[0]]) {
                if (next[1] + cur[1] >= result[next[0]]) continue;
                result[next[0]] = next[1] + cur[1];
                queue.add(new int[]{next[0], result[next[0]]});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        E = Integer.parseInt(temp[1]);

        info = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            info[i] = new ArrayList<>();
        }
        int start = Integer.parseInt(br.readLine());
        for (int i = 1; i <= E; i++) {
            temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            info[x].add(new int[]{y, Integer.parseInt(temp[2])});
        }

        dijkstra(start);
        for (int i = 1; i <= N; i++) {
            if (result[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(result[i]);
        }
    }
}