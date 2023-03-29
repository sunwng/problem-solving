package prob_1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainWithBitMask {
    static int N, K;
    static long[] wordBits;
    static int answer = 0;

    static int compareBits(boolean[] visit) {
        int count = 0;
        long visitBit = 0L;
        for (int i = 0; i < 26; i++) {
            if (!visit[i]) continue;
            visitBit |= (1 << i);
        }
        for (long wordBit : wordBits) {
            if ((wordBit & visitBit) == wordBit) count++;
        }
        return count;
    }

    static void searchCombination(int available, int start, boolean[] visit) {
        if (available == 0) {
            answer = Math.max(answer, compareBits(visit));
            return;
        }
        for (int i = start; i < 26; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            searchCombination(available - 1, i + 1, visit);
            visit[i] = false;
        }
    }

    static void strToBit(String[] words) {
        wordBits = new long[N];
        for (int i = 0; i < N; i++) {
            long bit = 0L;
            for (char alphabet : words[i].toCharArray()) {
                bit |= (1 << (alphabet - 'a'));
            }
            wordBits[i] = bit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        K = Integer.parseInt(given[1]);
        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
        strToBit(words);
        boolean[] visit = new boolean[26];
        visit['a' - 'a'] = true;
        visit['c' - 'a'] = true;
        visit['i' - 'a'] = true;
        visit['n' - 'a'] = true;
        visit['t' - 'a'] = true;
        searchCombination(K - 5, 0, visit);
        System.out.println(answer);
    }
}
