package prob_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] dn = {-1, 1, 0, 0};
    static int[] dm = {0, 0, -1, 1};
    static int[][] map;
    static List<List<int[]>> candidates;
    static List<int[]> nothings;
    public static void getCandidates(int start, List<int[]> candidate, boolean[] visit) {
        if (candidate.size() == 3) {
            candidates.add(candidate);
            return;
        }
        for (int i = start; i < nothings.size(); i++) {
            if (visit[i]) continue;
            List<int[]> temp = new ArrayList<>(candidate);
            temp.add(nothings.get(i));
            visit[i] = true;
            getCandidates(i, temp, visit);
            visit[i] = false;
        }
    }

    public static int bfs(int[][] map, List<int[]> viruses) {
        int count = 0;
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : viruses) {
            queue.add(virus);
            visit[virus[0]][virus[1]] = true;
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int nextN = current[0] + dn[i];
                int nextM = current[1] + dm[i];
                if (nextN < 0 || nextN >= N || nextM < 0 || nextM >= M) continue;
                if ((map[nextN][nextM] != 0) || visit[nextN][nextM]) continue;
                visit[nextN][nextM] = true;
                queue.add(new int[]{nextN, nextM});
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        M = Integer.parseInt(given[1]);
        map = new int[N][M];
        int walls = 0;
        List<int[]> viruses = new ArrayList<>();
        nothings = new ArrayList<>();
        candidates = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            given = br.readLine().split(" ");
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(given[m]);
                if (map[n][m] == 1) walls++;
                else if (map[n][m] == 2) viruses.add(new int[]{n, m});
                else nothings.add(new int[]{n, m});
            }
        }
        getCandidates(0, new ArrayList<>(), new boolean[nothings.size()]);
        int result = 0;
        for (List<int[]> candidate : candidates) {
            int[][] tempMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                tempMap[i] = map[i].clone();
            }
            for (int[] newWall : candidate) {
                tempMap[newWall[0]][newWall[1]] = 1;
            }
            result = Math.max(result, N * M - (walls + 3) - bfs(tempMap, viruses));
        }
        System.out.println(result);
    }
}