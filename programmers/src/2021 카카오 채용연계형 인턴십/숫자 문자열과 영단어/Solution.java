import java.util.*;
class Solution {
    public int solution(String s) {
        String[] repo = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < 10; i++) {
            if (s.contains(repo[i])) {
                s = s.replaceAll(repo[i], Integer.toString(i));
            }
        }
        return Integer.parseInt(s);
    }
}