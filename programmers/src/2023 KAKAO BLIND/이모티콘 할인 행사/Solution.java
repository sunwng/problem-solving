import java.util.*;

class Solution {
    int N, E;
    int[] rates = {10, 20, 30, 40};
    List<int[]> comb;
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        E = emoticons.length;
        comb = new ArrayList<>();
        getComb(0, E, new int[E]);
        
        int[] answer = new int[2];
        
        for (int[] combI : comb) {
            int count = 0;
            int money = 0;
            
            for (int[] user : users) {
                int tempMoney = 0;
                for (int i = 0; i < E; i++) {
                    if (combI[i] >= user[0]) {
                        tempMoney += (emoticons[i] * (100 - combI[i]) / 100);
                    }
                }
                if (tempMoney >= user[1]) count++;
                else money += tempMoney;
            }
            
            if (count > answer[0]) {
                answer[0] = count;
                answer[1] = money;
            } else if (count == answer[0]) {
                answer[1] = Math.max(answer[1], money);
            }
        }
        return answer;
    }
    
    void getComb(int current, int level, int[] order) {
        if (current == level) {
            comb.add(order.clone());
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            order[current] = rates[i];
            getComb(current + 1, level, order);
        }
        
    }
}