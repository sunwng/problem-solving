import java.util.*;

class Solution {
    int alMax = 0;
    int coMax = 0;
    
    boolean problemCheck(int al, int co, int[] problem) {
        return (al >= problem[0]) && (co >= problem[1]);
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        for (int[] problem : problems) {
            alMax = Math.max(alMax, problem[0]);
            coMax = Math.max(coMax, problem[1]);
        }
        if (alp >= alMax && cop >= coMax) return 0;
        else if (alp >= alMax) alMax = alp;
        else if (cop >= coMax) coMax = cop;
        
        
        int[][] dp = new int[alMax + 2][coMax + 2];
        for (int[] dpI : dp) {
            Arrays.fill(dpI, Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= alMax; i++) {
            for (int j = cop; j <= coMax; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                
                for (int[] problem : problems) {
                    if (!problemCheck(i, j, problem)) continue;
                    int alNext = i + problem[2];
                    int coNext = j + problem[3];
                    if (alNext > alMax) alNext = alMax;
                    if (coNext > coMax) coNext = coMax;
                    dp[alNext][coNext] = Math.min(dp[alNext][coNext], dp[i][j] + problem[4]);
                }
            }
        }
        
        return dp[alMax][coMax];
    }
}