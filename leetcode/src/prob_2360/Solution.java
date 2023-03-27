package prob_2360;

import java.util.*;

class Solution {
    int result = -1;
    boolean[] visit;

    public void dfs(int current, int[] edges, Map<Integer, Integer> path) {
        if (edges[current] == -1) return;
        int next = edges[current];
        if (!visit[next]) {
            visit[next] = true;
            path.put(next, path.get(current) + 1);
            dfs(next, edges, path);
        } else if (path.containsKey(next)) {
            result = Math.max(result, path.get(current) - path.get(next) + 1);
            return;
        }
    }

    public int longestCycle(int[] edges) {
        int N = edges.length;
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            if (edges[i] == -1) continue;
            Map<Integer, Integer> path = new HashMap<>();
            path.put(i, 0);
            dfs(i, edges, path);
        }
        return result;
    }
}