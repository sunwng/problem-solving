package prob_2390;

import java.util.*;

class Solution {
    public String removeStars(String s) {
        Deque<Character> deque = new LinkedList<>();

        for (char charI : s.toCharArray()) {
            if (charI == '*') {
                if (deque.isEmpty()) continue;
                deque.removeLast();
            } else {
                deque.addLast(charI);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.poll());
        }

        return sb.toString();
    }
}
