package prob_16724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int answer = 0;
    static int[][] map; // D: 0, R: 1, U: 2, L: 3
    static Map<Integer, int[]> direction;

    static void search(int[] start, int idx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int nextN = current[0] + direction.get(current[2])[0];
            int nextM = current[1] + direction.get(current[2])[1];
            int nextD = map[nextN][nextM];
            if (map[nextN][nextM] < 0 && map[nextN][nextM] > idx) {
                answer--;
                break;
            }
            if (map[nextN][nextM] == idx) continue;
            map[nextN][nextM] = idx;
            queue.add(new int[] {nextN, nextM, nextD});
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        M = Integer.parseInt(given[1]);
        map = new int[N][M];

        Map<String, Integer> converter = new HashMap<>();
        converter.put("D", 0);
        converter.put("R", 1);
        converter.put("U", 2);
        converter.put("L", 3);

        for (int i = 0; i < N; i++) {
            given = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = converter.get(given[j]);
            }
        }

        direction = new HashMap<>();
        direction.put(0, new int[] {1, 0});
        direction.put(1, new int[] {0, 1});
        direction.put(2, new int[] {-1, 0});
        direction.put(3, new int[] {0, -1});

        int idx = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] < 0) continue;
                int[] start = new int[] {i, j, map[i][j]};
                map[i][j] = idx;
                search(start, idx);
                answer++;
                idx--;
            }
        }
        System.out.println(answer);
    }
}
