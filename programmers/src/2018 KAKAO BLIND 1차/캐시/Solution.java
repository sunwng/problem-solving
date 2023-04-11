import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> cache = new ArrayDeque<>();
        
        for (String city : cities) {
            String target = city.toLowerCase();
            if (cache.contains(target)) {
                cache.remove(target);
                cache.add(target);
                answer++;
            } else {
                cache.add(target);
                if (cache.size() > cacheSize) cache.poll();
                
                answer += 5;
            }
        }
        
        return answer;
    }
}