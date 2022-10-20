import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int a = 1000000007;
        boolean[][] puddleCheck = new boolean[m+1][n+1];
        int[][] map = new int[m+1][n+1];
        map[1][1] = 1;
        for (int[] puddle : puddles) {
            puddleCheck[puddle[0]][puddle[1]] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (puddleCheck[i][j]) continue;
                if (i == 1 && j == 1) continue;
                map[i][j] = (map[i-1][j] + map[i][j-1]) % a;
            }
        }
        return map[m][n];
    }
}