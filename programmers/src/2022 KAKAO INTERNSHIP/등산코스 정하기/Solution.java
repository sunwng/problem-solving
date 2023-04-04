import java.util.*;

class Solution {
    
    List<List<int[]>> map;
    Set<Integer> summitSet;
    Set<Integer> gateSet;
    int summit = Integer.MAX_VALUE;
    int intensity = Integer.MAX_VALUE;
    
    boolean isSummit(int target) {
        return summitSet.contains(target);
    }
    
    boolean isGate(int target) {
        return gateSet.contains(target);
    }
    
    void search(int[] gates, int n) {
        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (int[] o1, int[] o2) -> o1[1] - o2[1]
        );
        for (int gate : gates) {
            queue.add(new int[] {gate, 0});
            cost[gate] = 0;
        }
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[1] > intensity) continue;
            
            for (int[] next : map.get(current[0])) {
                int nextIntensity = Math.max(next[1], current[1]);
                if (nextIntensity >= cost[next[0]]) continue;
                
                if (isSummit(next[0])) {
                    if (nextIntensity < intensity) {
                        summit = next[0];
                        intensity = nextIntensity;
                    } else if (nextIntensity == intensity) {
                        summit = Math.min(summit, next[0]);
                    }
                } else {
                    queue.add(new int[] {next[0], nextIntensity});
                }
                cost[next[0]] = nextIntensity;
            }
        }
        
    }
    
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            map.get(path[0]).add(new int[] {path[1], path[2]});
            map.get(path[1]).add(new int[] {path[0], path[2]});
        }
        summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }
        gateSet = new HashSet<>();
        for (int gate : gates) {
            gateSet.add(gate);
        }
        search(gates, n);
        
        return new int[] {summit, intensity};
    }
}