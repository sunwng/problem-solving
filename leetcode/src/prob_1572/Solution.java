package prob_1572;

import java.util.*;

class Solution {
    public int diagonalSum(int[][] mat) {
        int N = mat.length;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += mat[i][i];
            if (i == N - i - 1) continue;
            answer += mat[i][N - i - 1];
        }
        return answer;
    }
}
