import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        int max = t * m;
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (sb.length() <= max) {
            sb.append(Integer.toString(num++, n).toUpperCase());
        }
        String sequence = sb.toString();
        sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sb.append(Character.toString(sequence.charAt(i * m + p - 1)));
        }
        
        
        return sb.toString();
    }
}