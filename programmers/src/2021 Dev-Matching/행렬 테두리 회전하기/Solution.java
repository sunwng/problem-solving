import java.util.*;

class Solution {
    int[][] map;
    int[] answer;
    int R, C;
    
    void rotate(int[] query, int idx) {
        int min;
        int startR = query[0] - 1;
        int startC = query[1] - 1;
        int endR = query[2] - 1;
        int endC = query[3] - 1;
        
        int rightUp = map[query[0] - 1][query[3] - 1];
        int rightDown = map[query[2] - 1][query[3] - 1];
        int leftDown = map[query[2] - 1][query[1] - 1];
        min = Math.min(rightUp, Math.min(rightDown, leftDown));
        
        for (int i = endC; i > startC; i--) {
            map[startR][i] = map[startR][i - 1];
            min = Math.min(min, map[startR][i]);
        }
        for (int i = endR; i > startR; i--) {
            map[i][endC] = map[i - 1][endC];
            min = Math.min(min, map[i][endC]);
        }
        for (int i = startC; i < endC; i++) {
            map[endR][i] = map[endR][i + 1];
            min = Math.min(min, map[endR][i]);
        }
        for (int i = startR; i < endR; i++) {
            map[i][startC] = map[i + 1][startC];
            min = Math.min(min, map[i][startC]);
        }
        
        map[startR + 1][endC] = rightUp;
        map[endR][endC - 1] = rightDown;
        map[endR - 1][startC] = leftDown;
        answer[idx] = min;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        R = rows;
        C = columns;
        answer = new int[queries.length];
        map = new int[R][C];
        map[0][0] = 1;
        int N = R * C;
        for (int i = 1; i < N; i++) {
            map[i / C][i % C] = i + 1;
        }
        
        for (int i = 0; i < queries.length; i++) {
            rotate(queries[i], i);
        }
        
        return answer;
    }
}