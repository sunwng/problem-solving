import java.util.*;

class Solution {
    
    boolean isPrime(long target) {
        if (target <= 3) return target > 1;
        
        int max = (int) Math.sqrt(target);
        for (int i = 2; i <= max; i++) {
            if (target % i == 0) return false;
        }
        return true;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        
        String target = Integer.toString(n, k);
        String[] splitted = target.split("0");
        for (String candidate : splitted) {
            if (candidate.isEmpty() || candidate == "") continue;
            if (isPrime(Long.parseLong(candidate))) answer++;
        }
        
        return answer;
    }
}