import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> check = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character strI = s.charAt(i);
            if (strI == '(') {
                check.push(strI);
            } else {
                if (check.isEmpty()) {return false;}
                else {check.pop();}
            }
        }

        return check.isEmpty();
    }
}