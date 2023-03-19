package bj_2217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] info = new int[N];
        for (int i = 0; i < N; i++) {
            info[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(info);
        int result = 0;
        int num = 1;
        for (int i = N - 1; i >= 0; i--) {
            result = Math.max(result, info[i] * num);
            num++;
        }
        System.out.println(result);
    }
}