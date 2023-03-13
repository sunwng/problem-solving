package bj_18429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static List<Integer> weights;
    static int result;

    public static void dfs(int curWeight, int count, boolean[] visit) {
        if (curWeight < 500) return;
        if (count == weights.size()) {
            result++;
        }

        for (int i = 0; i < weights.size(); i++) {
            if (visit[i]) continue;
            visit[i] = true;
            dfs(curWeight - K + weights.get(i), count + 1, visit);
            visit[i] = false;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);

        weights = new ArrayList<>();
        str = br.readLine().split(" ");
        for (String strI : str) {
            weights.add(Integer.parseInt(strI));
        }
        result = 0;
        dfs(500, 0, new boolean[weights.size()]);
        System.out.println(result);
    }
}
