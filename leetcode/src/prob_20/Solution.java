package prob_20;

import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> partners = new HashMap<>();
        partners.put(')', '(');
        partners.put('}', '{');
        partners.put(']', '[');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (partners.containsValue(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;
                else if (stack.peek() == partners.get(s.charAt(i))) stack.pop();
                else return false;
            }
        }

        return stack.isEmpty();
    }
}
