import java.util.*;

class Solution {
    Map<String, Integer> dict;
    public int[] solution(String msg) {
        int N = msg.length();
        dict = new HashMap<>();
        for (int i = 65; i <= 90; i++) {
            dict.put(Character.toString(i), i - 64);
        }
        int last = 26;
        
        List<Integer> answer = new ArrayList<>();
        int idx = 0;
        while (idx < N) {
            int end = idx;
            while(end < N && dict.containsKey(msg.substring(idx, end + 1))) {
                end++;
            }
            answer.add(dict.get(msg.substring(idx, end)));
            if (end < N) dict.put(msg.substring(idx, end + 1), ++last);
            idx = end;
        }
        
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}