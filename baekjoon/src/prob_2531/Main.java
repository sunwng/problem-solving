package prob_2531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int d = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        int c = Integer.parseInt(temp[3]);
        int[] repo = new int[N+k-1];
        for (int i = 0; i < N; i++) {
            repo[i] = Integer.parseInt(br.readLine());
            if (i < k - 1) {
                repo[N+i] = repo[i];
            }
        }
        HashSet<Integer> set = new HashSet<Integer>();
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < k; j++) {
                set.add(repo[i+j]);
            }
            if (set.contains(c)) {
                result = Math.max(result, set.size());
            } else {
                result = Math.max(result, set.size() + 1);
            }
            set.clear();
        }
        System.out.println(result);
    }
}