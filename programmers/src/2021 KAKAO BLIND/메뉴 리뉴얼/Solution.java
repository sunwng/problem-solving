import java.util.*;

class Solution {
    int max;
    Map<String, Integer> count;
    
    void search(int start, int total, String comb, String[] orderArr) {
        if (total == 0) {
            count.putIfAbsent(comb, 0);
            count.replace(comb, count.get(comb) + 1);
            max = Math.max(max, count.get(comb));
            return;
        }
        
        for (int i = start; i < orderArr.length; i++) {
            search(i + 1, total - 1, comb + orderArr[i], orderArr);
        }
        
    }
    
    public String[] solution(String[] orders, int[] course) {
        count = new HashMap<>();
        
        List<String> answer = new ArrayList<>();
        for (int courseNum : course) {
            max = 0;
            for (String order : orders) {
                String[] orderArr = order.split("");
                Arrays.sort(orderArr);
                search(0, courseNum, "", orderArr);
            }
            for (String comb : count.keySet()) {
                if (count.get(comb) < Math.max(max, 2)) continue;
                answer.add(comb);
            }
            count.clear();
        }
        
        Collections.sort(answer);
        
        return answer.toArray(String[]::new);
    }
}