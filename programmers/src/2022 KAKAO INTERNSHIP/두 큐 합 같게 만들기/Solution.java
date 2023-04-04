import java.util.*;

class Solution {
    int N;
    
    public int solution(int[] queue1, int[] queue2) {
        N = queue1.length;
        int[] nums = new int[N * 2];
        long sum1 = 0;
        long sum2 = 0;
        
        for (int i = 0; i < N; i++) {
            nums[i] = queue1[i];
            sum1 += queue1[i];
            nums[i + N] = queue2[i];
            sum2 += queue2[i];
            
        }
        
        int q1Idx = 0;
        int q2Idx = N;
        int answer = 0;
        boolean flag = false;
        
        while (sum1 != sum2) {
            if (q1Idx == q2Idx) {
                answer = -1;
                break;
            }
            if (q1Idx >= N && q2Idx >= 0 && q2Idx < q1Idx) {
                answer = -1;
                break;
            }
            if (sum1 > sum2) {
                sum1 -= nums[q1Idx];
                sum2 += nums[q1Idx];
                q1Idx = (q1Idx + 1) % (N * 2);
            } else {
                sum2 -= nums[q2Idx];
                sum1 += nums[q2Idx];
                q2Idx = (q2Idx + 1) % (N * 2);
            }
            answer++;
            flag = true;
        }
        return answer;
    }
}