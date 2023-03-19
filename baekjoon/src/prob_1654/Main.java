package bj_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    static int N;
    static int[] info;

    public static boolean check(long numIn) {
        int count = 0;
        for (int infoI : info) {
            count += infoI / numIn;
        }
        return count >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        K = Integer.parseInt(temp[0]);
        N = Integer.parseInt(temp[1]);
        info = new int[K];

        for (int i = 0; i < K; i++) {
            info[i] = Integer.parseInt(br.readLine());
        }
        long start = 1;
        long end = (long) (Math.pow(2, 31) - 1);
//        int result = 1;

        while (start <= end) {
            long mid = ((long) start + end) / 2;

            if (check(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }
}