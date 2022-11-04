import java.util.*;
class Solution {
    public ArrayList<int[]> result;
    public static int[][] map;
    
    public boolean isGate(int numIn, int[] gates) {
        for (int gate : gates) {
            if (numIn == gate) return true;
        }
        return false;
    }
    
    public boolean isSummit(int numIn, int[] summits) {
        for (int summit : summits) {
            if (numIn == summit) return true;
        }
        return false;
    }
    
    public int[] dijkstra(int n, int[] gates, int[] summits) {
        Queue<Integer> queue = new LinkedList<>();
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        for (int gate : gates) {
            intensity[gate] = 0;
            queue.add(gate);
        }
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 1; i <= n; i++) {
                if (map[cur][i] > intensity[i]) continue;
                if (map[cur][i] == 0) continue;
                // if (map[cur][i] > intensity[i]) continue;
                int check = Math.max(intensity[cur], map[cur][i]);
                if (intensity[i] > check) {
                    intensity[i] = check;
                    queue.add(i);
                    // if (!isSummit(i, summits)) queue.add(i);
                }
                // if (!isSummit(i, summits)) queue.add(i);
            }
        }
        
        // for (int summit : summits) {
        //     result.add(new int[]{summit, intensity[summit]});
        // }
        
        int temp1 = Integer.MAX_VALUE;
        int temp2 = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensity[summit] < temp1) {
                temp1 = intensity[summit];
                temp2 = summit;
            }
        }
        return new int[]{temp2, temp1};
        
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        map = new int[n + 1][n + 1];
        
        for (int[] path : paths) {
            int s = path[0];
            int e = path[1];
            int c = path[2];
            if (isGate(s, gates) || isSummit(e, summits)) {
                map[s][e] = c;
            } else if (isGate(e, gates) || isSummit(s, summits)) {
                map[e][s] = c;
            } else {
                map[s][e] = c;
                map[e][s] = c;
            }
        }
        // result = new ArrayList<>();
        answer = dijkstra(n, gates, summits);
        // result.sort(new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] o1, int[] o2) {
        //         if (o1[1] != o2[1]) {
        //             return o1[1] - o2[1];
        //         } else {
        //             return o1[0] - o2[0];
        //         }
        //     }
        // });
        // answer = result.get(0);
        
        return answer;
    }
}