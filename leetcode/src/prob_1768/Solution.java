package prob_1768;

import java.util.*;

class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int idx = -1;
        while (idx < word1.length() - 1) {
            idx++;
            sb.append(word1.charAt(idx));
            if (idx >= word2.length()) continue;
            sb.append(word2.charAt(idx));
        }
        if (idx + 1 < word2.length()) sb.append(word2.substring(idx + 1));
        return sb.toString();
    }
}
