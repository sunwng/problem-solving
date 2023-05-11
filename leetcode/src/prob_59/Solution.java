package prob_59;

class Solution {
    public int[][] generateMatrix(int n) {
        int total = n * n;

        int num = 1;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int[][] answer = new int[n][n];
        int count = 0;
        while (true) {
            count++;
            if (count % 4 == 1) {
                for (int idx = left; idx <= right; idx++) {
                    answer[top][idx] = num++;
                }
            } else if (count % 4 == 2) {
                for (int idx = top + 1; idx <= bottom; idx++) {
                    answer[idx][right] = num++;
                }
            } else if (count % 4 == 3) {
                for (int idx = right - 1; idx >= left; idx--) {
                    answer[bottom][idx] = num++;
                }
            } else {
                for (int idx = bottom - 1; idx > top; idx--) {
                    answer[idx][left] = num++;
                }
            }

            if ((count % 4) == 0) {
                left++;
                top++;
                right--;
                bottom--;
            }
            if (num > total) break;
        }
        return answer;
    }
}
