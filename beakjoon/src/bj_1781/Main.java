package bj_1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            list.add(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])});
        }

        list.sort(Comparator.comparingInt(o -> o[0]));

        for (int[] info: list) {
            if (queue.size() > info[0]) continue;
            if (queue.size() == info[0]) {
                if (queue.peek() < info[1]) {
                    queue.poll();
                    queue.add(info[1]);
                }
            } else {
                queue.add(info[1]);
            }
        }

        long answer = 0;
        while (!queue.isEmpty()) {
            answer += queue.poll();
        }
        System.out.println(answer);
    }
}