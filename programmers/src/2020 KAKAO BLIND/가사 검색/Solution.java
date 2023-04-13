import java.util.*;

class Solution {
    Map<Integer, List<String>> origin;
    Map<Integer, List<String>> reverse;
    
    int lowerBound(String target, List<String> list) {
        int left = 0;
        int right = list.size() - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target.compareTo(list.get(mid)) < 0) right = mid - 1;
            else left = mid + 1;
        }
        
        return left;
    }
    
    int upperBound(String target, List<String> list) {
        int left = 0;
        int right = list.size() - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target.compareTo(list.get(mid)) > 0) left = mid + 1;
            else right = mid - 1;
        }
        
        return left;
    }
    
    public int[] solution(String[] words, String[] queries) {
        origin = new HashMap<>();
        reverse = new HashMap<>();
        for (int i = 1; i <= 10000; i++) {
            origin.put(i, new ArrayList<>());
            reverse.put(i, new ArrayList<>());
        }
        
        for (String word : words) {
            origin.get(word.length()).add(word);
            reverse.get(word.length()).add(new StringBuffer(word).reverse().toString());
        }
        
        for (int i = 1; i <= 10000; i++) {
            Collections.sort(origin.get(i), (String o1, String o2) -> o1.compareTo(o2));
            Collections.sort(reverse.get(i), (String o1, String o2) -> o1.compareTo(o2));
        }
        
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (origin.get(len).isEmpty()) answer[i] = 0;
            else if (queries[i].charAt(len - 1) == '?') {
                int from = lowerBound(queries[i].replaceAll("[?]", "a"), origin.get(len));
                int to = upperBound(queries[i].replaceAll("[?]", "z"), origin.get(len));
                answer[i] = to - from;
            } else {
                String reversed = new StringBuffer(queries[i]).reverse().toString();
                int from = lowerBound(reversed.replaceAll("[?]", "a"), reverse.get(len));
                int to = upperBound(reversed.replaceAll("[?]", "z"), reverse.get(len));
                answer[i] = to - from;
            }
        }
        
        
        return answer;
    }
}