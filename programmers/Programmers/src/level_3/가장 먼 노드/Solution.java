import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        int[] visited = new int[n+1];
        int[] shortest = new int[n+1];
        Arrays.fill(shortest, (int) Double.POSITIVE_INFINITY);
        shortest[1] = 0;
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int[] edgeI : edge) {
            graph.get(edgeI[0]).add(edgeI[1]);
            graph.get(edgeI[1]).add(edgeI[0]);
        }
        int curNode = 1;
        while (Arrays.stream(visited).sum() != n) {
            visited[curNode] = 1;
            for (int linkedNode : graph.get(curNode)) {
                shortest[linkedNode] = Math.min(shortest[linkedNode], shortest[curNode] + 1);
            }
            int minNum = (int) Double.POSITIVE_INFINITY;
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 1) continue;
                minNum = Math.min(minNum, shortest[i]);
                if (minNum == shortest[i]) {
                    curNode = i;
                }
            }
        }
        shortest[0] = 0;
        int maxLen = Arrays.stream(shortest).max().getAsInt();

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (shortest[i] == maxLen) answer++;
        }
        return answer;
    }
}