import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int N = s.length();
        
        for (int i = 1; i <= N; i++) {
            int idx = 0;
            int count = 0;
            String prev = "";
            StringBuilder sb = new StringBuilder();
            while (true) {
                if (idx + i > N) {
                    if (count > 1) sb.append(count);
                    if (idx >= N) break;
                    String target = s.substring(idx);
                    sb.append(target);
                    break;
                }
                String target = s.substring(idx, idx + i);
                if (prev.equals(target)) {
                    count++;
                } else {
                    if (count > 1) sb.append(count);
                    sb.append(target);
                    count = 1;
                }
                prev = target;
                idx += i;
            }
            answer = Math.min(answer, sb.length());
            
        }
        
        return answer;
    }
}