package prob_1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    static void union(int target1, int target2) {
        int parent1 = find(target1);
        int parent2 = find(target2);
        if (parent1 < parent2) parent[parent2] = parent1;
        else parent[parent1] = parent2;
    }

    static int find(int target) {
        if (parent[target] == target) return target;
        else return parent[target] = find(parent[target]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        M = Integer.parseInt(given[1]);
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            given = br.readLine().split(" ");
            int action = Integer.parseInt(given[0]);
            int target1 = Integer.parseInt(given[1]);
            int target2 = Integer.parseInt(given[2]);
            if (action == 0) {
                union(target1, target2);
            } else {
                if (target1 == target2) sb.append("YES").append("\n");
                else {
                    int parent1 = find(target1);
                    int parent2 = find(target2);
                    if (parent1 == parent2) sb.append("YES").append("\n");
                    else sb.append("NO").append("\n");
                }

            }

        }
        System.out.println(sb.toString());
    }
}
