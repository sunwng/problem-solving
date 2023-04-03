package prob_10816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] cards, targets;

    static int upperBound(int target) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target >= cards[mid]) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    static int lowerBound(int target) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (cards[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        String[] given = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(given[i]);
        }
        M = Integer.parseInt(br.readLine());
        targets = new int[M];
        given = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(given[i]);
        }
        Arrays.sort(cards);
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < M; i++) {
            int num = upperBound(targets[i]) - lowerBound(targets[i]);
            sj.add(Integer.toString(num));
        }
        System.out.println(sj);
    }
}