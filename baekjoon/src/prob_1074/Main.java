package prob_1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int find(int N, int r, int c) {
        if (N == 0) {
            return 0;
        }
        int half = (int) Math.pow(2, N - 1);
        if (r < half && c < half) return find(N - 1, r, c);
        else if (r < half && c >= half) return half * half + find(N - 1, r, c - half);
        else if (r >= half && c < half) return half * half * 2 + find(N - 1, r - half, c);
        else return half * half * 3 + find(N - 1, r - half, c - half);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        System.out.println(find(N, r, c));
    }
}