import java.util.*;

class Solution {
    int N, M;
    
    public int solution(int[][] board, int[][] skills) {
        N = board.length;
        M = board[0].length;
        int[][] dp = new int[N + 1][M + 1];
        
        for (int[] skill : skills) {
            int skillCheck = (int) Math.pow(-1, skill[0]);
            int startN = skill[1];
            int startM = skill[2];
            int endN = skill[3] + 1;
            int endM = skill[4] + 1;
            dp[startN][startM] += (skill[5] * skillCheck);
            dp[endN][endM] += (skill[5] * skillCheck);
            dp[startN][endM] -= (skill[5] * skillCheck);
            dp[endN][startM] -= (skill[5] * skillCheck);
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] += (dp[i][j - 1]);
            }
        }
        
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                dp[i][j] += dp[i - 1][j];
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += dp[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}