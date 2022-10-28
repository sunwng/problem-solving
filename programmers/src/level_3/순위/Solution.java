import java.util.*;
class Solution {
    public int[][] map;
    public int count(int target, int type) {
        HashSet<Integer> temp = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[map.length];
        visit[target] = true;
        queue.add(target);
        while (!queue.isEmpty()) {
            int curNum = queue.poll();
            for (int i = 1; i <= map.length-1; i++) {
                if (visit[i]) continue;
                if (map[curNum][i] == type) {
                    temp.add(i);
                    queue.add(i);
                    visit[i] = true;
                }
            }
        }
        return temp.size();
    }
    public int solution(int n, int[][] results) {
        int answer = 0;
        map = new int[n+1][n+1];
        for (int[] result : results) {
            map[result[0]][result[1]] = 1;
            map[result[1]][result[0]] = -1;
        }
        for (int i = 1; i <= n; i++) {
            if ((count(i, 1) + count(i, -1)) == (n - 1)) {
                answer++;
            }
        }
        return answer;
    }
}