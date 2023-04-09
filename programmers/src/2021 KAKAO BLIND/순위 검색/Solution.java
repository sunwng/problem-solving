import java.util.*;

class Solution {
    
    Map<String, List<Integer>> sortedInfos;
    
    int lowerBound(int target, List<Integer> targetList) {
        int left = 0;
        int right = targetList.size() - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = targetList.get(mid);
            if (midValue >= target) right = mid - 1;
            else left = mid + 1;
        }
        
        return targetList.size() - left;
    }
    
    void getCombination(int depth, String status, String[] splitted) {
        if (depth == 4) {
            sortedInfos.putIfAbsent(status, new ArrayList<>());
            sortedInfos.get(status).add(Integer.parseInt(splitted[4]));
            return;
        }
        
        for (int i = depth; i < 4; i++) {
            getCombination(i + 1, status + splitted[i], splitted);
            getCombination(i + 1, status + "-", splitted);
        }
        
    }
    
    public int[] solution(String[] info, String[] query) {
        sortedInfos = new HashMap<>();
        
        for (int i = 0; i < info.length; i++) {
            String[] splitted = info[i].split(" ");
            getCombination(0, "", splitted);
        }
        
        for (String status : sortedInfos.keySet()) {
            Collections.sort(sortedInfos.get(status));
        }
        
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] splitted = query[i].split(" ");
            String targetStatus = splitted[0] + splitted[2] + splitted[4] + splitted[6];
            if (!sortedInfos.containsKey(targetStatus)) continue;
            answer[i] = lowerBound(Integer.parseInt(splitted[7]), sortedInfos.get(targetStatus));
        }
        
        return answer;
    }
}