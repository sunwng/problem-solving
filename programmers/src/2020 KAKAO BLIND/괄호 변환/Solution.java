import java.util.*;

class Solution {
    
    boolean isCorrect(String target) {
        Stack stack = new Stack<>();
        for (char i : target.toCharArray()) {
            if (i == '(') stack.push(i);
            else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return true;
    }
    
    String[] split(String target) {
        int open = 0;
        int close = 0;
        String u = "";
        String v = "";
        for (char i : target.toCharArray()) {
            if (open != 0 && open == close) {
                v += i;
            } else {
                u += i;
                if (i == '(') open++;
                else close++;
            }
        }
        return new String[] {u, v};
    }
    
    String flip(String target) {
        if (target.length() <= 2) return "";
        String flipped = "";
        for (int i = 1; i < target.length() - 1; i++) {
            if (target.charAt(i) == '(') flipped += ")";
            else flipped += "(";
        }
        return flipped;
    }
    
    String solve(String target) {
        if (target.isEmpty()) return target;
        
        String[] splitted = split(target);
        String u = splitted[0];
        String v = splitted[1];
        if (isCorrect(u)) {
            return u + solve(v);
        } else {
            return "(" + solve(v) + ")" + flip(u);
        }
    }
    
    public String solution(String p) {
        if (p.isEmpty()) return "";
        
        return solve(p);
    }
}