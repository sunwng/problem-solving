import java.util.*;

class Solution {
    int B;
    Set<Set<String>> answer;
    
    void search(String[] user_id, String[] banned_id, Set<String> candidates) {
        if (candidates.size() == B) {
            if (check(candidates, banned_id)) {
                answer.add(new HashSet<>(candidates));
            }
            return;
        }
        
        for (String userId : user_id) {
            if (candidates.contains(userId)) continue;
            candidates.add(userId);
            search(user_id, banned_id, candidates);
            candidates.remove(userId);
        }
        
    }
    
    boolean check(Set<String> candidates, String[] banned_id) {
        int idx = 0;
        for (String candidate : candidates) {
            String banned = banned_id[idx++];
            if (candidate.length() != banned.length()) return false;
            for (int j = 0; j < candidate.length(); j++) {
                if (banned.charAt(j) == '*') continue;
                if (candidate.charAt(j) != banned.charAt(j)) return false;
            }
        }
        return true;
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        B = banned_id.length;
        answer = new HashSet<>();
        search(user_id, banned_id, new LinkedHashSet<>());
        return answer.size();
    }
}