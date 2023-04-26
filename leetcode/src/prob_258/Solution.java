package prob_258;

import java.util.*;

class Solution {
    public int addDigits(int num) {
        while (num >= 10) {
            int temp = 0;
            for (char i : Integer.toString(num).toCharArray()) {
                temp += (i - '0');
            }
            num = temp;
        }
        return num;
    }
}