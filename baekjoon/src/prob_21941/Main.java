package prob_21941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] given = br.readLine().split(" ");
            map.put(given[0], Integer.parseInt(given[1]));
        }

        int[] dp = new int[target.length() + 1];
        for (int i = 0; i < target.length(); i++) {
            for (String cand : map.keySet()) {
                if (cand.length() > i + 1) continue;
                if (target.substring(i + 1 - cand.length(), i + 1).contains(cand)) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[i + 1 - cand.length()] + map.get(cand));
                }
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i] + 1);
        }
        System.out.println(dp[target.length()]);
    }
}
