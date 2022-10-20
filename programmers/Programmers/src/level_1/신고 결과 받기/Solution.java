import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> reportCheck = new HashMap<>();
        for (String strI : id_list) {
            reportCheck.put(strI, new HashSet<String>());
        }
        for (String reportI : report) {
            String[] reportDiv = reportI.split(" ");
            reportCheck.get(reportDiv[1]).add(reportDiv[0]);
        }
        for (String id : id_list) {
            if (reportCheck.get(id).size() >= k) {
                for (String reportI : reportCheck.get(id)) {
                    answer[Arrays.asList(id_list).indexOf(reportI)]++;
                }
            }
        }

        return answer;
    }
}