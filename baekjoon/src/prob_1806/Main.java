package bj_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int S;
    static int N;
    static int[] info;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        S = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        info = new int[N];
        for (int i = 0; i < N; i++) {
            info[i] = Integer.parseInt(temp[i]);
        }
        result = Integer.MAX_VALUE;
        int sum = info[0];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            while (idx < N && sum < S) {
                idx++;
                if (idx != N) sum += info[idx];
            }
            if (idx == N) continue;
            result = Math.min(result, idx - i + 1);
            sum -= info[i];
        }
        if (result < Integer.MAX_VALUE) {
            System.out.println(result);
            return;
        } else {
            System.out.println(0);
            return;
        }

    }
}