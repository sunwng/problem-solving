package prob_2300;

import java.util.*;

class Solution {

    int binarySearch(int[] potions, long target) {
        int left = 0;
        int right = potions.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if ((long) potions[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }


    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] answer = new int[spells.length];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            answer[i] = potions.length - binarySearch(potions, (long) Math.ceil(success / (double) spells[i]));
        }
        return answer;
    }
}
