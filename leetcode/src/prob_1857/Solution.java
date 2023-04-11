package prob_1857;

import java.util.*;

class Solution {
    int N;

    public int largestPathValue(String colors, int[][] edges) {
        N = colors.length();
        int[] indegrees = new int[N];
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            indegrees[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
        }

        int[][] dp = new int[N][26];
        Queue<Integer> zeroInDegree = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (indegrees[i] != 0) continue;
            zeroInDegree.add(i);
            dp[i][colors.charAt(i) - 'a'] = 1;
        }

        int count = 0;
        int answer = 0;
        while (!zeroInDegree.isEmpty()) {
            int now = zeroInDegree.poll();
            count++;

            for (int next : map.get(now)) {
                indegrees[next]--;
                if (indegrees[next] == 0) zeroInDegree.add(next);

                for (int i = 0; i < 26; i++) {
                    if (colors.charAt(next) - 'a' == i) {
                        dp[next][i] = Math.max(dp[next][i], dp[now][i] + 1);
                    } else {
                        dp[next][i] = Math.max(dp[next][i], dp[now][i]);
                    }
                }
            }

            for (int i = 0; i < 26; i++) {
                answer = Math.max(answer, dp[now][i]);
            }

        }
        return count == N ? answer : -1;
    }
}
