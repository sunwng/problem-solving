import java.util.*;
class Solution {
    public boolean[] visit;
    public int count;
    public ArrayList<Integer> result = new ArrayList<>();

    public void dfs(int[] cards, int numIn) {
        if (visit[numIn]) return;
        count++;
        visit[numIn] = true;
        dfs(cards, cards[numIn-1]);
    }

    public int solution(int[] cards) {
        visit = new boolean[cards.length + 1];
        for (int i = 0; i < cards.length; i++) {
            if (visit[cards[i]]) continue;
            count = 0;
            dfs(cards, cards[i]);
            if (count == cards.length) return 0;
            else {
                result.add(count);
            }
        }
        result.sort(Collections.reverseOrder());
        int answer = result.get(0) * result.get(1);
        return answer;
    }
}