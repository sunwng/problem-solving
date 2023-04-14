import java.util.*;

class Solution {
    
    int N;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    List<List<int[]>> empties;
    List<List<int[]>> puzzles;
    int[][] board;
    int[][] table;
    
    public int solution(int[][] game_board, int[][] table_given) {
        N = game_board.length;
        board = game_board;
        table = table_given;
        empties = new ArrayList<>();
        puzzles = new ArrayList<>();
        
        int answer = 0;
        boolean[][] tableVisit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (table[i][j] == 0 || tableVisit[i][j]) continue;
                tableVisit[i][j] = true;
                getPuzzle(i, j, tableVisit);
            }
        }
        
        
        boolean[] usedP = new boolean[puzzles.size()];
        for (int num = 0; num < 4; num++) {
            boolean[][] boardVisit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1 || boardVisit[i][j]) continue;
                    boardVisit[i][j] = true;
                    getEmpty(i, j, boardVisit);
                }
            }
            
            
            boolean[] usedE = new boolean[empties.size()];
            for (int p = 0; p < puzzles.size(); p++) {
                if (usedP[p]) continue;
                int[] diffP = puzzles.get(p).get(0);
                for (int e = 0; e < empties.size(); e++) {
                    if (puzzles.get(p).size() != empties.get(e).size()) continue;
                    if (usedE[e]) continue;
                    int[] diffE = empties.get(e).get(0);
                    boolean flag = true;
                    for (int idx = 0; idx < puzzles.get(p).size(); idx++) {
                        if (empties.get(e).get(idx)[0] - diffE[0] != puzzles.get(p).get(idx)[0] - diffP[0]) {
                            flag = false;
                            break;
                        }
                        if (empties.get(e).get(idx)[1] - diffE[1] != puzzles.get(p).get(idx)[1] - diffP[1]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        usedE[e] = true;
                        mark(board, 1, empties.get(e));
                        usedP[p] = true;
                        answer += empties.get(e).size();
                        break;
                    }
                }
            }
            rotate();
            empties.clear();
        }
        return answer;
    }
    
    void rotate() {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[i][j] = board[N - 1 - j][i];
            }
        }
        board = rotated;
    }
    
    void mark(int[][] map, int type, List<int[]> list) { 
        // type 1 -> board, type 0 -> table
        for (int[] pos : list) {
            map[pos[0]][pos[1]] = type;
        }
    }
    
    void getPuzzle(int x, int y, boolean[][] visit) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {x, y});
        List<int[]> list = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            list.add(now);
            for (int dir = 0; dir < 4; dir++) {
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];
                if (isOut(nx, ny) || visit[nx][ny] || table[nx][ny] == 0) continue;
                visit[nx][ny] = true;
                q.add(new int[] {nx, ny});
            }
        }
        list.sort((int[] o1, int[] o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        puzzles.add(list);
    }
    
    void getEmpty(int x, int y, boolean[][] visit) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {x, y});
        List<int[]> list = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            list.add(now);
            for (int dir = 0; dir < 4; dir++) {
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];
                if (isOut(nx, ny) || visit[nx][ny] || board[nx][ny] == 1) continue;
                visit[nx][ny] = true;
                q.add(new int[] {nx, ny});
            }
        }
        list.sort((int[] o1, int[] o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        empties.add(list);
    }
    
    boolean isOut(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
    
}