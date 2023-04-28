package prob_839;

import java.util.*;

class Solution {

    int N;
    int[] parents;

    int find(int idx) {
        if (parents[idx] == idx) return idx;
        else return parents[idx] = find(parents[idx]);
    }

    void union(int idx1, int idx2) {
        int parent1 = find(idx1);
        int parent2 = find(idx2);
        if (parent1 < parent2) parents[parent2] = parent1;
        else parents[parent1] = parent2;
    }

    boolean isSimilar(String target1, String target2) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (count > 2) break;
            if (target1.charAt(i) != target2.charAt(i)) count++;
        }

        return count % 2 == 0;
    }

    public int numSimilarGroups(String[] strs) {
        N = strs[0].length();
        parents = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            parents[i] = i;
        }


        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (!isSimilar(strs[i], strs[j])) continue;
                union(i, j);
            }
        }

        int answer = 0;
        for (int i = 0; i < strs.length; i++) {
            if (parents[i] == i) answer++;
        }
        return answer;
    }
}
