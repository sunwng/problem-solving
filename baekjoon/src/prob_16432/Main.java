package prob_16432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<List<Integer>> ducks;

    static class Duck {
        int level;
        int num;
        String path;
        public Duck(int level, int num, String path) {
            this.level = level;
            this.num = num;
            this.path = path;
        }
    }

    static String solve() {
        PriorityQueue<Duck> queue = new PriorityQueue<>((Duck o1, Duck o2) -> o2.level - o1.level);
        boolean[][] visit = new boolean[N + 1][10];
        for (int duck : ducks.get(0)) {
            queue.add(new Duck(1, duck, Integer.toString(duck)));
            visit[1][duck] = true;
        }

        while (!queue.isEmpty()) {
            Duck now = queue.poll();
            if (now.level == N) {
                StringJoiner sj = new StringJoiner("\n");
                for (char charI : now.path.toCharArray()) {
                    sj.add(Character.toString(charI));
                }
                return sj.toString();
            }

            for (int next : ducks.get(now.level)) {
                if (next == now.num) continue;
                if (visit[now.level + 1][next]) continue;
                visit[now.level + 1][next] = true;
                queue.add(new Duck(now.level + 1, next, now.path + Integer.toString(next)));
            }

        }

        return "-1";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ducks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ducks.add(new ArrayList<>());
            String[] given = br.readLine().split(" ");
            for (int j = 1; j < given.length; j++) {
                ducks.get(i).add(Integer.parseInt(given[j]));
            }
        }

        System.out.println(solve());


    }
}