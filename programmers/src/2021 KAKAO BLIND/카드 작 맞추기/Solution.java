import java.util.*;

class Solution {
    
    class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int N = 0;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    Pair[][] pics;
    
    Set<int[]> orders = new HashSet<>();
    
    int[] toIntArr(String target) {
        int[] arr = new int[N];
        String[] splitted = target.split("");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(splitted[i]);
        }
        return arr;
    }
    
    void getOrders(int level, String order) {
        if (level == N) {
            orders.add(toIntArr(order));
            return;
        }
        
        for (int i = 1; i <= 6; i++) {
            if (pics[i][0].x == -1) continue;
            if (order.contains(Integer.toString(i))) continue;
            getOrders(level + 1, order + i);
        }
        
    }
    
    int[][] copyMap(int[][] map) {
        int[][] copied = new int[4][4];
        for (int i = 0; i < 4; i++) {
            copied[i] = map[i].clone();
        }
        return copied;
    }
    
    boolean isOut(int x, int y) {
        return x < 0 || x > 3 || y < 0 || y > 3;
    }
    
    int getLastStep(int x, int y, int dir, int[][] map) {
        int step = 1;
        while (true) {
            int nx = x + dx[dir] * step;
            int ny = y + dy[dir] * step;
            if (isOut(nx, ny)) {
                step--;
                break;
            }
            if (map[nx][ny] != 0) {
                break;
            }
            step++;
        }
        return step;
    }
    
    int bfs(Pair start, Pair target, int[][] map) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(start.x, start.y));
        int[][] cost = new int[4][4];
        for (int[] costI : cost) {
            Arrays.fill(costI, -1);
        }
        cost[start.x][start.y] = 0;
        
        while (!queue.isEmpty()) {
            Pair now = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int step = getLastStep(now.x, now.y, dir, map);
                for (int i = 0; i < 2; i++) {
                    int nx = now.x + dx[dir] * (int) Math.pow(step, i);
                    int ny = now.y + dy[dir] * (int) Math.pow(step, i);
                    if (isOut(nx, ny)) continue;
                    if (cost[nx][ny] != -1) continue;
                    cost[nx][ny] = cost[now.x][now.y] + 1;
                    queue.add(new Pair(nx, ny));
                }
            }
        }
        return cost[target.x][target.y];
    }
    
    void markAsDone(int target, int[][] map) {
        map[pics[target][0].x][pics[target][0].y] = 0;
        map[pics[target][1].x][pics[target][1].y] = 0;
    }
    
    public int solution(int[][] board, int r, int c) {
        pics = new Pair[7][2];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                pics[i][j] = new Pair(-1, -1);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) continue;
                if (pics[board[i][j]][0].x == -1) {
                    pics[board[i][j]][0] = new Pair(i, j);
                    N++;
                } else {
                    pics[board[i][j]][1] = new Pair(i, j);
                }
            }
        }
        getOrders(0, "");
        int answer = Integer.MAX_VALUE;
        
        for (int[] order : orders) {
            int[][] map = copyMap(board);
            int temp = 0;
            boolean check = false;
            int result1 = bfs(new Pair(r, c), pics[order[0]][0], map) + bfs(pics[order[0]][0], pics[order[0]][1], map);
            int result2 = bfs(new Pair(r, c), pics[order[0]][1], map) + bfs(pics[order[0]][1], pics[order[0]][0], map);
            markAsDone(order[0], map);
            if (result1 > result2) check = true;
            else check = false;
            temp += Math.min(result1, result2);
            
            for (int i = 1; i < N; i++) {
                if (check) {
                    result1 = bfs(pics[order[i - 1]][0], pics[order[i]][0], map) + bfs(pics[order[i]][0], pics[order[i]][1], map);
                    result2 = bfs(pics[order[i - 1]][0], pics[order[i]][1], map) + bfs(pics[order[i]][1], pics[order[i]][0], map);
                } else {
                    result1 = bfs(pics[order[i - 1]][1], pics[order[i]][0], map) + bfs(pics[order[i]][0], pics[order[i]][1], map);
                    result2 = bfs(pics[order[i - 1]][1], pics[order[i]][1], map) + bfs(pics[order[i]][1], pics[order[i]][0], map);
                }
                if (result1 > result2) check = true;
                else check = false;
                temp += Math.min(result1, result2);
                
                markAsDone(order[i], map);
            }
            answer = Math.min(answer, temp);
        }
        
        return answer + 2 * N;
    }
}