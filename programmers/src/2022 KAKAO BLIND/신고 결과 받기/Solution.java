import java.util.*;

class Solution {
    int N;
    public int[] solution(String[] id_list, String[] report, int k) {
        N = id_list.length;
        
        Map<String, Set<String>> count = new HashMap<>();
        Map<String, Integer> indexTable = new HashMap<>();
        for (int i = 0; i < N; i++) {
            count.put(id_list[i], new HashSet<>());
            indexTable.put(id_list[i], i);
        }
        
        for (String reportI : report) {
            String[] splitted = reportI.split(" ");
            String from = splitted[0];
            String to = splitted[1];
            count.get(to).add(from);
        }
        
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            if (count.get(id_list[i]).size() < k) continue;
            for (String reporter : count.get(id_list[i])) {
                answer[indexTable.get(reporter)]++;
            }
        }
        
        return answer;
    }
}