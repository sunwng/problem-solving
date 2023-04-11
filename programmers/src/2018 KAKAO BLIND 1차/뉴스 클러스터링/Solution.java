import java.util.*;

class Solution {
    
    String parse(String target) {
        return target.toUpperCase();
    }
    
    void split(String target, Map<String, Integer> map) {
        for (int i = 0; i < target.length() - 1; i++) {
            String temp = target.substring(i, i + 2);
            if (temp.replaceAll("[^A-Z]", "").length() != 2) continue;
            map.putIfAbsent(temp, 0);
            map.replace(temp, map.get(temp) + 1);
        }
    }
    
    void exchange(Map<String, Integer> map1, Map<String, Integer> map2) {
        for (String key : map1.keySet()) {
            map2.putIfAbsent(key, 0);
        }
        for (String key : map2.keySet()) {
            map1.putIfAbsent(key, 0);
        }
    }
    
    public int solution(String str1, String str2) {
        str1 = parse(str1);
        str2 = parse(str2);
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        split(str1, map1);
        split(str2, map2);
        if (map1.isEmpty() && map2.isEmpty()) return 65536;
        exchange(map1, map2);
        
        int intersection = 0;
        int union = 0;
        
        for (String key : map1.keySet()) {
            intersection += Math.min(map1.get(key), map2.get(key));
            union += Math.max(map1.get(key), map2.get(key));
        }
        return 65536 * intersection / union;
    }
}