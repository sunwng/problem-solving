import java.util.*;

class Solution {
    String enterMsg = "님이 들어왔습니다.";
    String leaveMsg = "님이 나갔습니다.";
    
    public String[] solution(String[] records) {
        Map<String, String> users = new HashMap<>();
        for (String record : records) {
            String[] splitted = record.split(" ");
            String action = splitted[0];
            String uid = splitted[1];
            if ("Leave".equals(action)) continue;
            String nick = splitted[2];
            users.put(uid, nick);
        }
        
        List<String> answer = new ArrayList<>();
        for (String record : records) {
            String[] splitted = record.split(" ");
            String action = splitted[0];
            String uid = splitted[1];
            if ("Enter".equals(action)) {
                answer.add(users.get(uid) + enterMsg);
            } else if ("Leave".equals(action)) {
                answer.add(users.get(uid) + leaveMsg);
            }
        }
        
        return answer.toArray(String[]::new);
    }
}