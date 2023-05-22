package prob_347;

import java.util.*;

class Solution {

    int OFFSET = 10000;

    public int[] topKFrequent(int[] nums, int k) {

        List<int[]> count = new ArrayList<>();
        for (int i = 0; i < OFFSET * 2 + 1; i++) {
            count.add(new int[] {i - OFFSET, 0});
        }

        for (int num : nums) {
            count.get(num + OFFSET)[1]++;
        }

        Collections.sort(count, (int[] o1, int[] o2) -> o2[1] - o1[1]);

        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            answer[i] = count.get(i)[0];
        }

        return answer;
    }
}
