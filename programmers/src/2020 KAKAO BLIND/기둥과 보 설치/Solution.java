import java.util.*;

class Solution {
    int N;
    boolean[][] pillar;
    boolean[][] beam;
    
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        pillar = new boolean[N + 1][N + 1];
        beam = new boolean[N + 1][N + 1];
        int count = 0;
        
        for (int[] cmd : build_frame) {
            int x = cmd[0];
            int y = cmd[1];
            int type = cmd[2];
            int action = cmd[3];
            
            if (type == 0) { // Pillar
                if (action == 0) { // Remove
                    pillar[x][y] = false;
                    if (!isPossible()) pillar[x][y] = true;
                    else count--;
                } else { // Add
                    if (checkPillar(x, y)) {
                        pillar[x][y] = true;
                        count++;
                    }
                }
            } else { // Beam
                if (action == 0) { // Remove
                    beam[x][y] = false;
                    if (!isPossible()) beam[x][y] = true;
                    else count--;
                } else { // Add
                    if (checkBeam(x, y)) {
                        beam[x][y] = true;
                        count++;
                    }
                }
            }
        }
        int[][] answer = new int[count][3];
        int idx = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillar[i][j]) answer[idx++] = new int[] {i, j, 0};
                if (beam[i][j]) answer[idx++] = new int[] {i, j, 1};
            }
        }
        
        
        return answer;
    }
    
    boolean isPossible() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillar[i][j] && !checkPillar(i, j)) return false;
                else if (beam[i][j] && !checkBeam(i, j)) return false;
            }
        }
        return true;
    }
    
    boolean checkBeam(int x, int y) {
        if (pillar[x][y - 1] || pillar[x + 1][y - 1]) return true;
        else if (x > 0 && x < N - 1 && beam[x - 1][y] && beam[x + 1][y]) return true;
        return false;
    }
    
    boolean checkPillar(int x, int y) {
        if (y == 0) return true;
        else if (pillar[x][y - 1]) return true;
        else if ((x > 0 && beam[x - 1][y]) || beam[x][y]) return true;
        return false;
    }
    
}