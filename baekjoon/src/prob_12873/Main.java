package prob_12873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        int idx = 0;
        for (int i = 1; i < N; i++) {
            int target = (int) (((long) Math.pow(i, 3) + idx - 1) % list.size());
            if (target == list.size() - 1) idx = 0;
            else idx = target;
            list.remove(target);
        }

        System.out.println(list.get(0));
    }
}
