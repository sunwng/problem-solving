package prob_16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int M;
    static int k;
    static int[] money;
    static int[] parent;

    static int getParent(int child) {
        if (parent[child] == child) return child;
        else return getParent(parent[child]);
    }

    static void union(int idx1, int idx2) {
        int rootidx1 = getParent(idx1);
        int rootidx2 = getParent(idx2);
        if (money[rootidx1] > money[rootidx2]) {
            parent[rootidx1] = rootidx2;
        } else {
            parent[rootidx2] = rootidx1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);
        k = Integer.parseInt(input1[2]);

        String[] input2 = br.readLine().split(" ");
        money = new int[N + 1];
        for (int i = 0; i < N; i++) {
            money[i + 1] = Integer.parseInt(input2[i]);
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int friend1 = Integer.parseInt(temp[0]);
            int friend2 = Integer.parseInt(temp[1]);
            union(friend1, friend2);
        }

        boolean[] visit = new boolean[N + 1];
        int result = 0;

        for (int i = 1; i <= N; i++) {
            int parent = getParent(i);
            if (visit[parent]) {
                visit[i] = true;
                continue;
            }
            result += money[parent];
            visit[parent] = true;
            visit[i] = true;
        }

        if (result > k) {
            System.out.println("Oh no");
        } else {
            System.out.println(result);
        }

    }
}