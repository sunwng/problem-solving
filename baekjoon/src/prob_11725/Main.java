package prob_11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> map;
    static int[] parents;

    static void search(int cur) {
        for (int next : map.get(cur)) {
            if (parents[cur] == next) continue;
            parents[next] = cur;
            search(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }
        parents = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            String[] given = br.readLine().split(" ");
            int num1 = Integer.parseInt(given[0]);
            int num2 = Integer.parseInt(given[1]);
            map.get(num1).add(num2);
            map.get(num2).add(num1);
        }
        search(1);
        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }


    }
}