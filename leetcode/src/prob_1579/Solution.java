package prob_1579;

import java.util.*;

class Solution {
    int[] parentA;
    int[] parentB;


    public int maxNumEdgesToRemove(int n, int[][] edges) {
        parentA = new int[n + 1];
        parentB = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parentA[i] = i;
            parentB[i] = i;
        }

        Arrays.sort(edges, (int[] o1, int[] o2) -> o2[0] - o1[0]);

        int alice = 1;
        int bob = 1;
        int remove = 0;

        for (int[] edge : edges) {
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];
            if (type == 3) {
                boolean aliceCheck = union(parentA, u, v);
                boolean bobCheck = union(parentB, u, v);
                if (aliceCheck) alice++;
                if (bobCheck) bob++;
                if (!aliceCheck && !bobCheck) remove++;
            } else if (type == 1) {
                boolean aliceCheck = union(parentA, u, v);
                if (aliceCheck) alice++;
                else remove++;
            } else {
                boolean bobCheck = union(parentB, u, v);
                if (bobCheck) bob++;
                else remove++;
            }
        }

        return (alice != n || bob != n) ? -1 : remove;
    }

    int find(int[] parent, int idx) {
        if (parent[idx] == idx) return idx;
        else return parent[idx] = find(parent, parent[idx]);
    }

    boolean union(int[] parent, int idx1, int idx2) {
        int parent1 = find(parent, idx1);
        int parent2 = find(parent, idx2);

        if (parent1 == parent2) return false;
        else if (parent1 < parent2) {
            parent[parent2] = parent1;
            return true;
        } else {
            parent[parent1] = parent2;
            return true;
        }
    }

}
