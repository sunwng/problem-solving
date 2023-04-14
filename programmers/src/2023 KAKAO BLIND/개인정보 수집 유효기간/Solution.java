import java.util.*;

class Solution {
    int M = 28;
    int Y = 28 * 12;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> termTable = new HashMap<>();
        
        for (String term : terms) {
            String[] splitted = term.split(" ");
            termTable.put(splitted[0], Integer.parseInt(splitted[1]) * M);
        }
        int todayInt = dateToInt(today);
        for (int i = 0; i < privacies.length; i++) {
            String[] splitted = privacies[i].split(" ");
            int expireInt = dateToInt(splitted[0]) + termTable.get(splitted[1]);
            if (expireInt <= todayInt) answer.add(i + 1);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    int dateToInt(String date) {
        String[] splitted = date.split("\\.");
        int dateInt = 0;
        dateInt += (Integer.parseInt(splitted[0]) * Y);
        dateInt += (Integer.parseInt(splitted[1]) * M);
        dateInt += (Integer.parseInt(splitted[2]));
        return dateInt;
    }
}