import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int minVal;
    public static int maxVal;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static boolean check() {
        boolean flag = false;
        visited = new boolean[N][N];
        List<int[]> allList;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                allList = new LinkedList<>();
                int sum = dfs(i, j, allList);
                if (allList.size() > 1) {
                    flag = true;
                    change(sum, allList);
                }
            }
        }
        return flag;
    }

    public static int dfs(int x, int y, List<int[]> listIn) {
        visited[x][y] = true;
        listIn.add(new int[]{x, y});
        int sum = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
            if (!visited[nextX][nextY]) {
                int diff = Math.abs(map[x][y] - map[nextX][nextY]);
                if (diff >= minVal && diff <= maxVal) {
                    sum += dfs(nextX, nextY, listIn);
                }
            }
        }
        return sum;
    }

    public static void change(int sum, List<int[]> allList) {
        int newNum = sum / allList.size();
        for (int[] posI : allList) {
            map[posI[0]][posI[1]] = newNum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        minVal = Integer.parseInt(temp[1]);
        maxVal = Integer.parseInt(temp[2]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int result = 0;
        while (true) {
            if (check()) {
                result++;
            } else {
                break;
            }
        }
        System.out.println(result);
    }
}