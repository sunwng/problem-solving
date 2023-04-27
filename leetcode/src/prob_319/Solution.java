package prob_319;

import java.util.*;

class Solution {
    public int bulbSwitch(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int root = (int) Math.sqrt(i);
            if (root * root == i) answer++;
        }

        return answer;
    }
}
