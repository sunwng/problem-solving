import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        int N = queue1.length;
        int limit = N * 2;
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < N; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        if ((sum1 + sum2) % 2 == 1) return -1;
        long mean = (sum1 + sum2) / 2;
        int index1 = 0;
        int index2 = 0;
        
        while(index1 < limit && index2 < limit) {
            // if (sum1 == sum2) return index1 + index2;
            
            if (sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.add(q1.poll());
                index1++;
            } else if (sum2 > sum1) {
                sum2 -= q2.peek();
                sum1 += q2.peek();
                q1.add(q2.poll());
                index2++;
            } else {
                return index1 + index2;
            }
        }
        
        return -1;
    }
}