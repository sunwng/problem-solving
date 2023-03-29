package prob_1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static String[] words;
    static int answer = 0;

    static int count(boolean[] learned) {
        int result = 0;
        for (String word : words) {
            boolean flag = true;
            for (char wordI : word.toCharArray()) {
                if (!learned[wordI - 'a']) {
                    flag = false;
                    break;
                }
            }
            if (flag) result++;
        }
        return result;
    }

    static void search(int available, int start, boolean[] learned) {
        if (available == 0) {
            answer = Math.max(answer, count(learned));
            return;
        }

        for (int i = start; i < 26; i++) {
            if (learned[i]) continue;
            learned[i] = true;
            search(available - 1, i, learned);
            learned[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        K = Integer.parseInt(given[1]);
        words = new String[N];
        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine().replaceAll("anta|tica", "");
        }
        boolean[] learned = new boolean[26];
        learned['a' - 'a'] = true;
        learned['c' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;
        search(K - 5, 0, learned);
        System.out.println(answer);
    }
}