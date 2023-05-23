package prob_703;

import java.util.*;

class KthLargest {

    private final int idx;
    private final PriorityQueue<Integer> sorted;

    public KthLargest(int k, int[] nums) {
        this.idx = k;
        sorted = new PriorityQueue<>();
        for (int num : nums) {
            sorted.add(num);
            if (sorted.size() > idx) sorted.poll();
        }
    }

    public int add(int val) {
        sorted.add(val);
        if (sorted.size() > idx) sorted.poll();
        return sorted.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
