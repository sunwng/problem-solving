package prob_1697;

import java.util.*;

class Solution {

    int[] parents;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;

        Arrays.sort(edgeList, (int[] o1, int[] o2) -> o1[2] - o2[2]);

        int Q = queries.length;
        int[][] sortedQueries = new int[Q][4];
        for (int i = 0; i < Q; i++) {
            sortedQueries[i] = new int[] {queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, (int[] o1, int[] o2) -> o1[2] - o2[2]);

        boolean[] results = new boolean[Q];
        int E = edgeList.length;
        int idx = 0;
        for (int[] query : sortedQueries) {
            while (idx < E && edgeList[idx][2] < query[2]) {
                union(edgeList[idx][0], edgeList[idx][1]);
                idx++;
            }
            results[query[3]] = (find(query[0]) == find(query[1]));
        }

        return results;
    }

    int find(int x) {
        if (parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX < parentY) parents[parentY] = parentX;
        else parents[parentX] = parentY;
    }

}
