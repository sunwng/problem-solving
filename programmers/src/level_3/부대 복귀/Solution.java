import java.util.*;

class Solution {
    int N;
    List<List<Integer>> map;
    
    int[] dijkstra(int start) {
        int[] result = new int[N + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] o1, int[] o2) -> o1[1] - o2[1]
        );
        pq.add(new int[] {start, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (current[1] != result[current[0]]) continue;
            for (int next : map.get(current[0])) {
                if (current[1] + 1 >= result[next]) continue;
                result[next] = current[1] + 1;
                pq.add(new int[] {next, result[next]});
            }
        }
        
        return result;
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        Arrays.fill(answer, -1);
        N = n;
        map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }
        int[] cost = dijkstra(destination);
        for (int i = 0; i < sources.length; i++) {
            if (cost[sources[i]] == Integer.MAX_VALUE) continue;
            answer[i] = cost[sources[i]];
        }
        
        return answer;
    }
}