import java.util.*;
class Solution {
    int[] parent;
    public int findParent(int nodeIn) {
        if (nodeIn == parent[nodeIn]) return nodeIn;
        return findParent(parent[nodeIn]);
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Arrays.sort(costs, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        for (int[] cost : costs) {
            int fromParent = findParent(cost[0]);
            int toParent = findParent(cost[1]);
            if (fromParent == toParent) continue;
            parent[toParent] = fromParent;
            answer += cost[2];
        }


        return answer;
    }
}