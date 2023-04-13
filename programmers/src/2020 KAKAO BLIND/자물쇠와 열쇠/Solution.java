import java.util.*;

class Solution {
    int M, N, L;
    int[][] map;
    
    int[][] rotate(int[][] key) {
        int[][] rotated = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                rotated[i][j] = key[M - j - 1][i];
            }
        }
        return rotated;
    }
    
    void reset(int[][] target) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                target[i][j] = map[i][j];
            }
        }
    }
    
    boolean check(int[][] target) {
        for (int i = M - 1; i < M - 1 + N; i++) {
            for (int j = M - 1; j < M - 1 + N; j++) {
                if (target[i][j] != 1) return false;
            }
        }
        return true;
    }
    
    void add(int[][] key, int[][] target, int x, int y) {
        for (int i = x; i < x + M; i++) {
            for (int j = y; j < y + M; j++) {
                target[i][j] += key[i - x][j - y];
            }
        }
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        L = N + (M - 1) * 2;
        map = new int[L][L];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        
        int[][] tempMap = new int[L][L];
        for (int i = 0; i < L; i++) {
            tempMap[i] = map[i].clone();
        }
        
        for (int idx = 0; idx < 4; idx++) {
            
            for (int i = 0; i <= N + M - 2; i++) {
                for (int j = 0; j <= N + M - 2; j++) {
                    add(key, tempMap, i, j);
                    if (check(tempMap)) return true;
                    reset(tempMap);
                }
            }
            key = rotate(key);
        }
        
        
        return false;
    }
}