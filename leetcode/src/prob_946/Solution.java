package prob_946;

import java.util.*;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int pushIdx = 0;
        int popIdx = 0;

        while (true) {
            if (popIdx == popped.length) break;

            if (!stack.isEmpty() && stack.peek() == popped[popIdx]) {
                stack.pop();
                popIdx++;
            } else if (pushIdx < pushed.length) {
                stack.push(pushed[pushIdx++]);
            } else break;
        }
        return stack.isEmpty();
    }
}
