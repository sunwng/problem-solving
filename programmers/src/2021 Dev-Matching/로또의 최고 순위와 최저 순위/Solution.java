import java.util.*;

class Solution {
    
    int getRank(int target) {
        if (target >= 6) return 1;
        else if (target == 5) return 2;
        else if (target == 4) return 3;
        else if (target == 3) return 4;
        else if (target == 2) return 5;
        else return 6;
    }
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        Set<Integer> wins = new HashSet<>();
        for (int win_num : win_nums) {
            wins.add(win_num);
        }
        int corrects = 0;
        int zeros = 0;
        for (int lotto : lottos) {
            if (wins.contains(lotto)) corrects++;
            else if (lotto == 0) zeros++;
        }
        
        answer[0] = getRank(corrects + zeros);
        answer[1] = getRank(corrects);
        
        return answer;
    }
}