package prob_606;

import java.util.*;

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int N = flowerbed.length;
        for (int i = 0; i < N; i++) {
            if (n == 0) break;
            if ((flowerbed[i] == 0) && (i == 0 || flowerbed[i - 1] == 0) && (i == N - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n == 0;
    }
}