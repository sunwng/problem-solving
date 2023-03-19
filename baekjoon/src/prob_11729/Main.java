package bj_11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int from, int to, int num) {
        if (num == 1) {
            sb.append(from + " " + to + "\n");
            return;
        }
        dfs(from, 6 - from - to, num - 1);
        sb.append(from + " " + to + "\n");
        dfs(6 - from - to, to, num - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        sb.append((int) Math.pow(2, num) - 1).append("\n");
        dfs(1, 3, num);
        System.out.println(sb);
    }
}