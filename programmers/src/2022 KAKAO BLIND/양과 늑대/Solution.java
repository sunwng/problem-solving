import java.util.*;

class Solution {
    int answer = 0;
    int N;
    int[] values;
    int[] left;
    int[] right;
    boolean[] visit;
    
    void search(int state) {
        if (visit[state]) return;
        visit[state] = true;
        int total = 0;
        int wolves = 0;
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) == 0) continue;
            total++;
            if (values[i] == 1) wolves++;
        }
        
        int sheeps = total - wolves;
        if (wolves >= sheeps) return;
        answer = Math.max(answer, sheeps);
        
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) == 0) continue;
            if (left[i] != -1) search(state | (1 << left[i]));
            if (right[i] != -1) search(state | (1 << right[i]));
        }
        
    }
    
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        values = info;
        left = new int[N];
        right = new int[N];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        visit = new boolean[1 << N];
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (left[from] == -1) left[from] = to;
            else right[from] = to;
        }
        search(1);
        
        return answer;
    }
}