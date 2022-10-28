import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            queue.add(i);
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (visit[j]) continue;
                    if (computers[curNode][j] == 1) {
                        queue.add(j);
                        visit[j] = true;
                    }
                }
            }
            answer++;
        }

        return answer;
    }
}