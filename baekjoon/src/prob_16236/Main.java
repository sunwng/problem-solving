package prob_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int size = 2;
    static int eat = 0;
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int checkAvailable(int[] shark, int[] candidate) {
        int cost = 0;
        boolean[][] visit = new boolean[N][N];
        visit[shark[0]][shark[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {shark[0], shark[1], 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == candidate[0] && current[1] == candidate[1]) {
                cost = current[2];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                if (map[nextX][nextY] > size) continue;
                if (visit[nextX][nextY]) continue;
                visit[nextX][nextY] = true;
                queue.add(new int[] {nextX, nextY, current[2] + 1});
            }
        }
        return cost;
    }

    static int[] searchTarget(int[] shark) {
        int[] target = new int[3];

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (int[] o1, int[] o2) -> {
                    if (o1[2] == o2[2]) {
                        if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                        else return Integer.compare(o1[0], o2[0]);
                    }
                    else return o1[2] - o2[2];
                }
        );

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= size || map[i][j] == 9 || map[i][j] == 0) continue;
                int cost = checkAvailable(shark, new int[] {i, j});
                if (cost == 0) continue;
                pq.add(new int[] {i, j, cost});
            }
        }
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = checkAvailable(shark, current);
            if (cost != 0) {
                target[0] = current[0];
                target[1] = current[1];
                target[2] = cost;
                break;
            }
        }
        return target;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int[] shark = new int[2];

        for (int i = 0; i < N; i++) {
            String[] given = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(given[j]);
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                }
            }
        }
        int time = 0;

        while (true) {
            int[] target = searchTarget(shark);
            if (target[2] == 0) break;
            map[shark[0]][shark[1]] = 0;
            map[target[0]][target[1]] = 9;
            shark[0] = target[0];
            shark[1] = target[1];
            eat++;
            time += target[2];
            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }
}
