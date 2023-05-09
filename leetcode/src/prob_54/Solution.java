package prob_54;

import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;
        int total = (bottom + 1) * (right + 1);
        int count = 0;
        while (answer.size() < total) {
            if (count % 4 == 0) {
                for (int i = left; i <= right; i++) {
                    answer.add(matrix[top][i]);
                }
                top++;
            } else if (count % 4 == 1) {
                for (int i = top; i <= bottom; i++) {
                    answer.add(matrix[i][right]);
                }
                right--;
            } else if (count % 4 == 2) {
                for (int i = right; i >= left; i--) {
                    answer.add(matrix[bottom][i]);
                }
                bottom--;
            } else {
                for (int i = bottom; i >= top; i--) {
                    answer.add(matrix[i][left]);
                }
                left++;
            }
            count++;
        }

        return answer;
    }
}
