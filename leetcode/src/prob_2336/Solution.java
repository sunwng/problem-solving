package prob_2336;

import java.util.*;

class SmallestInfiniteSet {

    Set<Integer> popped;
    PriorityQueue<Integer> pq;

    public SmallestInfiniteSet() {
        popped = new HashSet<>();
        pq = new PriorityQueue<>();
        pq.add(1);
    }

    public int popSmallest() {
        if (!pq.contains(pq.peek() + 1) && !popped.contains(pq.peek() + 1)) pq.add(pq.peek() + 1);
        popped.add(pq.peek());
        return pq.poll();
    }

    public void addBack(int num) {
        if (popped.contains(num)) {
            pq.add(num);
            popped.remove(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
