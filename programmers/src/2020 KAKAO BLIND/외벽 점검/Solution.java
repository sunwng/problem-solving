import java.util.*;

class Solution {
    boolean flag = false;
    int N, D, W;
    int[] weaks;
    int[] dists;
    
    boolean search(int[] sequence) {
        List<Integer> temp = new ArrayList<>();
        for (int weak : weaks) temp.add(weak);
        for (int i = 0; i < W; i++) {
            int count = 0;
            for (int cur : sequence) {
                int start = temp.get(count) + dists[cur];
                while (count < W && temp.get(count) <= start) {
                    count++;
                }
                if (count == W) return true;
            }
            temp.add(temp.get(0) + N);
            temp.remove(0);
        }
        return false;
    }
    
    void solve(int current, int level, int visit, int[] sequence) {
        if (current == level) {
            if (search(sequence)) flag = true;
            return;
        }
        for (int i = 0; i < D; i++) {
            if ((visit & (1 << i)) != 0) continue;
            sequence[current] = i;
            solve(current + 1, level, (visit | (1 << i)), sequence);
        }
    } 
    
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        D = dist.length;
        W = weak.length;
        weaks = weak;
        dists = dist;
        
        for (int i = 1; i <= D; i++) {
            solve(0, i, 0, new int[i]);
            if (flag) return i;
        }
        
        return -1;
    }
}