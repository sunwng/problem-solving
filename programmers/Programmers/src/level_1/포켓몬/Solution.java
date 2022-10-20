import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int pickLen = nums.length/2;
        // HashMap<Integer, Integer> check = new HashMap<>();
        HashSet<Integer> check = new HashSet<>();
        // for (int intI : nums) {
        //     check.put(intI, check.getOrDefault(intI, 0) + 1);
        // }
        for (int intI : nums) {
            check.add(intI);
        }
        if (check.size() <= pickLen) {
            answer = check.size();
        } else {
            answer = pickLen;
        }
        return answer;
    }
}