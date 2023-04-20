package prob_1719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<int[]>> map;
    static int[][] answer;

    static void dijkstra(int start) {
        int[] cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        int[] first = new int[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> o1[2] - o2[2]);
        for (int[] next : map.get(start)) {
            first[next[0]] = next[0];
            cost[next[0]] = next[1];
            pq.add(new int[] {next[0], next[1], next[0]});
        }

        while (!pq.isEmpty()) {
            int[] now = pq.poll(); // 0 노드 1 코스트 2 첫시작

            if (now[1] != cost[now[0]]) continue;
            for (int[] next : map.get(now[0])) {
                if (now[1] + next[1] >= cost[next[0]]) continue;
                first[next[0]] = now[2];
                cost[next[0]] = now[1] + next[1];
                pq.add(new int[] {next[0], now[1] + next[1], now[2]});
            }
        }
        for (int i = 1; i <= N; i++) {
            answer[start][i] = first[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        M = Integer.parseInt(given[1]);
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            given = br.readLine().split(" ");
            int node1 = Integer.parseInt(given[0]);
            int node2 = Integer.parseInt(given[1]);
            int cost = Integer.parseInt(given[2]);
            map.get(node1).add(new int[] {node2, cost});
            map.get(node2).add(new int[] {node1, cost});
        }
        answer = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int j = 1; j <= N; j++) {
                if (answer[i][j] == 0) sj.add("-");
                else sj.add(Integer.toString(answer[i][j]));
            }
            sb.append(sj.toString()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
