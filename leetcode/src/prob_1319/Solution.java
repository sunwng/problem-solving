package prob_1319;

import java.util.*;

class Solution {
    int[] parents;

    int find(int x) {
        if(parents[x] == x) return x;
        return find(parents[x]);
    }

    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x <= y) parents[y] = x;
        else parents[x] = y;
    }


    public int makeConnected(int n, int[][] connections) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] connection : connections) {
            union(connection[0], connection[1]);
        }
        int min = 0;
        int roots = 0;
        for (int i = 0; i < n; i++) {
            if (i == find(i)) roots++;
            else min++;
        }
        if ((connections.length - min) >= (roots - 1)) return roots - 1;
        else return -1;
    }
}