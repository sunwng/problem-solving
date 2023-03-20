package prob_1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            List<int[]> papers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                papers.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
            }

            papers.sort(Comparator.comparingInt((int[] o) -> o[0]));
            int count = 1;
            int prev = papers.get(0)[1];

            for (int i = 1; i < N; i++) {
                if (papers.get(i)[1] < prev) {
                    prev = papers.get(i)[1];
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}