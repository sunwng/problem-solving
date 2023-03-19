package prob_1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int L;
    static List<Integer> diff;

    static int counter(int target) {
        int result = 0;
        for (int diffI : diff) {
            if (diffI < target) break;
            result += (int) Math.ceil((double) diffI / target) - 1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);

        if (N == 0) {
            System.out.println((int) Math.ceil((double) L / (M + 1)));
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(input[i]));
        }

        diff = new ArrayList<>();
        int prev = pq.poll();
        diff.add(prev);
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            diff.add(cur - prev);
            prev = cur;
        }
        diff.add(L - prev);
        diff.sort(Collections.reverseOrder());

        int left = 1;
        int right = diff.get(0);

        while (left <= right) {
            int mid = (left + right) / 2;
            if (counter(mid) <= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}