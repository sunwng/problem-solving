package bj_1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        PriorityQueue<Integer> B = new PriorityQueue<>(Collections.reverseOrder());
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(temp[i]);
        }
        temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            B.add(Integer.parseInt(temp[i]));
        }
        Arrays.sort(A);
        int result = 0;
        for (int i = 0; i < N; i++) {
            result += B.poll() * A[i];
        }
        System.out.println(result);
    }
}