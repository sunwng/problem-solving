package prob_1491;

import java.util.*;

class Solution {
    public double average(int[] salary) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int salaryI : salary) {
            sum += salaryI;
            min = Math.min(min, salaryI);
            max = Math.max(max, salaryI);
        }
        sum -= min;
        sum -= max;
        return (double) sum / (salary.length - 2);
    }
}
