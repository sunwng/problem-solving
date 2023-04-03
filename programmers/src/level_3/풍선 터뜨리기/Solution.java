import java.util.*;

class Solution {
    public int solution(int[] a) {
        int N = a.length;
        int answer = N;
        
        int[] fromLeft = new int[N];
        fromLeft[0] = a[0];
        int[] fromRight = new int[N];
        fromRight[N - 1] = a [N - 1];
        for (int i = 1; i < N; i++) {
            fromLeft[i] = Math.min(fromLeft[i - 1], a[i]);
            fromRight[N - i - 1] = Math.min(fromRight[N - i], a[N - i - 1]);
        }
        
        for (int i = 0; i < N; i++) {
            if (a[i] > fromLeft[i] && a[i] > fromRight[i]) answer--;
        }
        
        return answer;
    }
}