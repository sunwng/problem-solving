import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }
        int N = set.size();
        
        Map<String, Integer> map = new HashMap<>();
        
        int left = 0;
        int right = 0;
        int max = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while (true) {
            if (map.size() == N) {
               int size = right - left + 1;
                if (size < max) {
                    max = size;
                    answer[0] = left + 1;
                    answer[1] = right;
                }
                if (map.get(gems[left]) == 1) {
                    map.remove(gems[left]);
                } else {
                    map.replace(gems[left], map.get(gems[left]) - 1);
                }
                left++;
            } else if (right == gems.length) {
                break;
            } else {
                map.putIfAbsent(gems[right], 0);
                map.replace(gems[right], map.get(gems[right]) + 1);
                right++;
            }
        }
        return answer;
    }
}