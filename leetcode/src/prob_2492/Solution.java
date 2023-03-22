package prob_2492;

import java.util.*;

class Solution {

    Map<Integer, List<int[]>> map;

    int bfs(int start, int n) {
        int result = Integer.MAX_VALUE;
        boolean[] visit = new boolean[n + 1];
        visit[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for (int[] path : map.get(current)) {
                result = Math.min(result, path[1]);
                if (visit[path[0]]) continue;
                visit[path[0]] = true;
                queue.add(path[0]);
            }
        }
        return result;
    }

    public int minScore(int n, int[][] roads) {
        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            map.get(road[0]).add(new int[]{road[1], road[2]});
            map.get(road[1]).add(new int[]{road[0], road[2]});
        }
        return bfs(1, n);
    }
}