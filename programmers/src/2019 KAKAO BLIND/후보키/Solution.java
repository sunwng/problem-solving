import java.util.*;

class Solution {
    int N, C;
    String[][] data;
    Set<Integer> answer;
    
    boolean check(int state) {
        Set<String> pool = new HashSet<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            if ((state & 1 << i) == 0) continue;
            cols.add(i);
        }
        for (String[] dataI : data) {
            StringJoiner sj = new StringJoiner("|");
            for (int c : cols) {
                sj.add(dataI[c]);
            }
            if (pool.contains(sj.toString())) return false;
            pool.add(sj.toString());
        }
        return true;
    }
    
    void search(int start, int cur, int level, int state) {
        if (cur == level) {
            if (!check(state)) return;
            for (int answerI : answer) {
                if ((state & answerI) == answerI) return;
            }
            answer.add(state);
            return;
        }
        for (int i = start; i < C; i++) {
            if ((state & 1 << i) != 0) continue;
            search(i, cur + 1, level, state | (1 << i));
        }
    }
    
    public int solution(String[][] relation) {
        data = relation;
        N = relation.length;
        C = relation[0].length;
        answer = new HashSet<>();
        
        for (int i = 1; i <= C; i++) {
            search(0, 0, i, 0);
        }
        
        
        return answer.size();
    }
}