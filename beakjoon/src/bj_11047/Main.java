package bj_11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);
        int[] info = new int[N];
        for (int i = 0; i < N; i++) {
            info[i] = Integer.parseInt(br.readLine());
        }
        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            count += K / info[i];
            K %= info[i];
        }
        System.out.println(count);
    }
}