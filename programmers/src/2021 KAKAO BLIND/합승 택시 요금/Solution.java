import java.util.*;

class Solution {
    class Road {
        int target;
        int cost;
        
        public Road(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }
    
    int N;
    List<List<Road>> roads;
    
    int[] dijkstra(int start) {
        int[] cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] o1, int[] o2) -> o1[1] - o2[1]
        );
        pq.add(new int[] {start, 0});
        
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if (now[1] != cost[now[0]]) continue;
            for (Road road : roads.get(now[0])) {
                if (now[1] + road.cost >= cost[road.target]) continue;
                cost[road.target] = now[1] + road.cost;
                pq.add(new int[] {road.target, now[1] + road.cost});
            }
        }
        return cost;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        roads = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            roads.get(fare[0]).add(new Road(fare[1], fare[2]));
            roads.get(fare[1]).add(new Road(fare[0], fare[2]));
        }
        
        int[][] minCost = new int[N + 1][N + 1];
        
        for (int i = 1; i <= N; i++) {
            minCost[i] = dijkstra(i);
        }
        int answer = Integer.MAX_VALUE;
        
        for (int i = 1; i <= n; i++) {
            if (minCost[s][i] == Integer.MAX_VALUE) continue;
            int toA = minCost[i][a];
            int toB = minCost[i][b];
            answer = Math.min(answer, minCost[s][i] + toA + toB);
        }
        
        return answer;
    }
}