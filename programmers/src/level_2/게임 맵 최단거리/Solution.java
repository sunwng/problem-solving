import java.util.*;
class Solution {
    int[] dn = {-1, 1, 0, 0};
    int[] dm = {0, 0, -1, 1};
    public void bfs(int[][] maps, int n, int m, int[][] visitCheck) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        visitCheck[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curN = curPos[0];
            int curM = curPos[1];
            for (int i = 0; i < 4; i++) {
                int nextN = curN + dn[i];
                int nextM = curM + dm[i];
                if (nextN >= 0 && nextN < n && nextM >= 0 && nextM < m && visitCheck[nextN][nextM] == 0 && maps[nextN][nextM] == 1) {
                    queue.add(new int[] {nextN, nextM});
                    visitCheck[nextN][nextM] = visitCheck[curN][curM] + 1;
                }
            }
        }
    }
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int answer = 0;
        int[][] visitCheck = new int[n][m];
        bfs(maps, n, m, visitCheck);
        if (visitCheck[n-1][m-1] == 0) {
            answer = -1;
        } else {
            answer = visitCheck[n-1][m-1];
        }
        return answer;
    }
}