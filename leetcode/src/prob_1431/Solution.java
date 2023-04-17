package prob_1431;

import java.util.*;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        List<Boolean> answer = new ArrayList<>();
        for (int candy : candies) {
            answer.add(candy + extraCandies >= max);
        }

        return answer;
    }
}
