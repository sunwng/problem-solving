import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] nums = new int[n][n];
        
        int N = n * (n + 1) / 2;
        
        int checker = 0;
        int num = 1;
        int x = 0;
        int y = 0;
        int max = n;
        
        while (max > 0) {
            if (checker % 3 == 0) {
                for (int i = 0; i < max; i++) {
                    nums[x][y] = num;
                    num++;
                    if (i < max - 1) x++;
                    else y++;
                }
            } else if (checker % 3 == 1) {
                for (int i = 0; i < max; i++) {
                    nums[x][y] = num;
                    num++;
                    if (i < max - 1) y++;
                    else {
                        x--;
                        y--;
                    }
                }
            } else {
                for (int i = 0; i < max; i++) {
                    nums[x][y] = num;
                    num++;
                    if (i < max - 1) {
                        x--;
                        y--;
                    } else x++;
                }
            }
            max--;
            checker++;
        }
        
        int[] answer = new int[N];
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0) break;
                answer[idx++] = nums[i][j];
            }
        }
        
        return answer;
    }
}