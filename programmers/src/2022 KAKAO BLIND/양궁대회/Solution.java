import java.util.*;

class Solution {
    int[] answer = {-1};
    
    int max = 0;
    int[] uppeach;
    
    int calculate(int[] lion) {
        int result = 0;
        for (int i = 0; i <= 10; i++) {
            if (lion[i] == 0 && uppeach[i] == 0) continue;
            if (lion[i] <= uppeach[i]) {
                result -= (10 - i);
            } else {
                result += (10 - i);
            }
        }
        return result;
    }
    
    boolean lowCheck(int[] candidate) {
        for (int i = 10; i >= 0; i--) {
            if (answer[i] > candidate[i]) return false;
            else if (answer[i] < candidate[i]) return true;
        }
        return true;
    }
    
    void search(int start, int left, int[] status) {
        if (left == 0) {
            int result = calculate(status);
            if (result <= 0) return;
            if (result > max) {
                max = result;
                answer = status.clone();
            } else if (result == max && lowCheck(status)) {
                answer = status.clone();
            }
            return;
        }
        
        for (int i = start; i <= 10; i++) {
            status[i]++;
            search(i, left - 1, status);
            status[i]--;
        }
    }
    
    
    public int[] solution(int n, int[] info) {
        uppeach = info;
        search(0, n, new int[11]);
        
        return answer;
    }
}