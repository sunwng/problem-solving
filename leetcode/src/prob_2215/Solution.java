package prob_2215;

import java.util.*;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        answer.add(new ArrayList<>());
        Set<Integer> set1 = new HashSet<>();
        for (int num1 : nums1) set1.add(num1);
        Set<Integer> set2 = new HashSet<>();
        for (int num2: nums2) set2.add(num2);

        for (int num1 : set1) {
            if (set2.contains(num1)) continue;
            answer.get(0).add(num1);
        }
        for (int num2 : set2) {
            if (set1.contains(num2)) continue;
            answer.get(1).add(num2);
        }
        return answer;
    }
}
