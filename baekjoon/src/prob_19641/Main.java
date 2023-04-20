package prob_19641;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visit;
    static int[][] answer;
    static List<List<Integer>> map;

    static boolean isLeaf(int target) {
        for (int node : map.get(target)) {
            if (!visit[node]) return false;
        }
        return true;
    }

    static int search(int cur, int left) {
        if (isLeaf(cur)) {
            return answer[cur][0];
        }

        for (int next : map.get(cur)) {
            if (visit[next]) continue;
            visit[next] = true;
            answer[next][0] = left + 1;
            answer[next][1] = search(next, answer[next][0]) + 1;
            left = answer[next][1];
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            String[] given = br.readLine().split(" ");
            int target = Integer.parseInt(given[0]);
            for (int j = 1; j < given.length - 1; j++) {
                map.get(target).add(Integer.parseInt(given[j]));
            }
            Collections.sort(map.get(target));
        }
        int root = Integer.parseInt(br.readLine());
        answer = new int[N + 1][2];
        answer[root][0] = 1;
        answer[root][1] = (map.size() - 1) * 2;
        visit = new boolean[N + 1];
        visit[root] = true;
        search(root, 1);

        for (int i = 1; i <= N; i++) {
            System.out.println(i + " " + answer[i][0] + " " + answer[i][1]);
        }

    }
}