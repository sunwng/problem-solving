import java.util.*;

class Solution {
    int N;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        N = n;
        long answer = 0;
        
        int idx = Math.max(getLast(N - 1, deliveries), getLast(N - 1, pickups));
        while (idx >= 0) {
            answer += ((idx + 1) * 2);
            
            int countD = 0;
            int idxD = idx;
            while (countD < cap) {
                if (idxD == -1) break;
                if (deliveries[idxD] == 0) {
                    idxD--;
                    continue;
                }
                deliveries[idxD]--;
                countD++;
            }
            
            int countP = 0;
            int idxP = idx;
            while (countP < cap) {
                if (idxP == -1) break;
                if (pickups[idxP] == 0) {
                    idxP--;
                    continue;
                }
                pickups[idxP]--;
                countP++;
            }
            
            idx = Math.max(getLast(idx, deliveries), getLast(idx, pickups));
        }
        
        
        return answer;
    }
    
    int getLast(int start, int[] target) {
        while (start >= 0) {
            if (target[start] > 0) break;
            start--;
        }
        return start; // 다 0 이면 -1 리턴함
    }
}