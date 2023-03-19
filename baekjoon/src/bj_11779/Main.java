package bj_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int E;
    static int N;
    static int[] cost;
    static int[] path;
    static List<int[]>[] info;

    public static void dijkstra(int start) {
        cost = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        cost[start] = 0;
        pq.add(new int[]{start, cost[start]});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[1] != cost[cur[0]]) continue;
            for (int[] next : info[cur[0]]) {
                if (cur[1] + next[1] >= cost[next[0]]) continue;
                cost[next[0]] = cur[1] + next[1];
                path[next[0]] = cur[0];
                pq.add(new int[]{next[0], cost[next[0]]});
            }
        }
    }

    public static void find(int start, int end) {
        int count = 1;
        Stack<Integer> stack = new Stack<>();
        stack.add(end);
        while (start != end) {
            end = path[end];
            stack.add(end);
            count++;
        }
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        info = new ArrayList[N + 1];
        for (int i = 1; i<= N; i++) {
            info[i] = new ArrayList<>();
        }

        for (int i = 1; i <= E; i++) {
            String[] temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);
            info[s].add(new int[]{e, c});
        }
        String[] temp = br.readLine().split(" ");
        int start = Integer.parseInt(temp[0]);
        int end = Integer.parseInt(temp[1]);
        dijkstra(start);
        System.out.println(cost[end]);
        find(start, end);
    }
}