import java.util.*;
class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        int answer = -1;
        Set<Integer>[] dp = new HashSet[9];
        dp[1] = new HashSet<Integer>();
        dp[1].add(N);

        for (int i = 2; i <= 8; i++) {
            dp[i] = new HashSet<Integer>();
            for (int j = 1; j <= i/2; j++) {
                Set<Integer> setI = dp[j];
                Set<Integer> setJ = dp[i-j];
                for (int numI : setI) {
                    for (int numJ : setJ) {
                        dp[i].add(numI + numJ);
                        dp[i].add(numI - numJ);
                        dp[i].add(numJ - numI);
                        dp[i].add(numI * numJ);
                        if (numI != 0 && numJ != 0) {
                            dp[i].add(numI / numJ);
                            dp[i].add(numJ / numI);
                        }
                    }
                }
                dp[i].add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
            if (dp[i].contains(number)) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}