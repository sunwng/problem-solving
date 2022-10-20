import java.util.*;
class Solution {
    int answer = 0;
    int[] dX = {1, -1, 0, 0};
    int[] dY = {0, 0, 1, -1};
    int[][] map;
    boolean[][] visit;

    public void checkArea(int[] rectangleI) {
        int cX1 = rectangleI[0] * 2;
        int cX2 = rectangleI[2] * 2;
        int cY1 = rectangleI[1] * 2;
        int cY2 = rectangleI[3] * 2;
        for (int i = cX1; i <= cX2; i++) {
            for (int j = cY1; j <= cY2; j++) {
                if (map[i][j] == -1) {continue;}
                map[i][j] = -1;
                if (i == cX1 || i == cX2 || j == cY1 || j == cY2) {
                    map[i][j] = 1;
                }
            }
        }
    }
    public void bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Integer> moveQ = new LinkedList<>();
        moveQ.add(characterX);
        moveQ.add(characterY);
        while (!moveQ.isEmpty()) {
            int curX = moveQ.poll();
            int curY = moveQ.poll();
            visit[curX][curY] = true;
            if (curX == itemX && curY == itemY) {
                answer = map[curX][curY];
                break;
                // if (answer == 0) {
                //     answer = map[curX][curY];
                // } else {
                //     answer = Math.min(answer, map[curX][curY]);
                // }
            }
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dX[i];
                int nextY = curY + dY[i];
                if (map[nextX][nextY] != 1 || visit[nextX][nextY]) continue;
                if (nextX > 100 || nextX < 0 || nextY > 100 || nextY < 0) continue;
                moveQ.add(nextX);
                moveQ.add(nextY);
                map[nextX][nextY] = map[curX][curY] + 1;
            }
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];
        visit = new boolean[101][101];
        for (int[] rectI : rectangle) {
            checkArea(rectI);
        }
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        return answer/2;
    }
}