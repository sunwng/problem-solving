package prob_1046;

import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) return stones[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            pq.add(stone);
        }
        int answer = 0;
        while (true) {
            int first = pq.poll();
            int second = pq.poll();

            if (first > second) pq.add(first - second);
            if (pq.size() == 1) {
                answer = pq.poll();
                break;
            } else if (pq.isEmpty()) break;
        }

        return answer;
    }
}
