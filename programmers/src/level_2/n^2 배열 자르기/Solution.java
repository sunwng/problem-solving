import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int) (right - left) + 1;
        int[] answer = new int[size];
        
        int startR = (int) (left / n);
        int startC = (int) (left % n);
        int endR = (int) (right / n);
        int endC = (int) (right % n);
        int num = 0;
        if (startC >= startR) num = startC + 1;
        else num = startR + 1;
        
        for (int i = 0; i < size; i++) {
            
            answer[i] = num;
            
            if (startC >= startR) num++;
            
            if (startC == n - 1) {
                startC = 0;
                startR++;
                num = startR + 1;
            } else {
                startC++;
            }
            
        }
        
        
        
        return answer;
    }
}