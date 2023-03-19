package prob_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    static int N;
    static int[] wires;

    public static boolean checker(long target) {
        int count = 0;
        for (int wire : wires) {
            count += (wire / target);
        }
        return count >= N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        wires = new int[K];
        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(wires);
        long left = 1;
        long right = wires[K - 1] + 1;

        while (left != right) {
            long mid = (left + right) / 2;
            if (checker(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(right);
    }
}