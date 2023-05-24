package prob_2542;

import java.util.*;

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int N = nums1.length;
        int[][] pairs = new int[N][2];
        for (int i = 0; i < N; i++) {
            pairs[i] = new int[] {nums2[i], nums1[i]};
        }
        Arrays.sort(pairs, (int[] o1, int[] o2) -> o2[0] - o1[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long answer = 0;
        long sum = 0;
        for (int[] pair : pairs) {
            sum += pair[1];
            pq.add(pair[1]);
            if (pq.size() > k) {
                sum -= pq.poll();
            }
            if (pq.size() == k) {
                answer = Math.max(answer, sum * pair[0]);
            }
        }
        return answer;
    }
}
