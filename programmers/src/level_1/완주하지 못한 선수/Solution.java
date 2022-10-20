import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> check = new HashMap<>();
        String answer = "";
        for (String strI : participant) {
            check.put(strI, check.getOrDefault(strI, 0) + 1);
        }
        for (String strI : completion) {
            check.replace(strI, check.get(strI) - 1);
        }
        for (String strI : check.keySet()) {
            if (check.get(strI) != 0) {
                answer = strI;
                break;
            }
        }

        return answer;
    }
}