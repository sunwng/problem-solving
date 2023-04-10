package prob_1857;

import java.util.*;

class Solution {
    int N;

    int getMax(int[] target) {
        int result = 0;
        for (int i : target) {
            result = Math.max(result, i);
        }
        return result;
    }

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

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (indegrees[i] != 0) continue;
            queue.add(i);
        }

        int visit = 0;
        int answer = 0;
        int[][] count = new int[N][26];
        for (int i = 0; i < N; i++) {
            count[i][colors.charAt(i) - 'a']++;
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            visit++;
            for (int next : map.get(now)) {
                for (int i = 0; i < 26; i++) {
                    int adder = (colors.charAt(next) - 'a') == i ? 1 : 0;
                    count[next][i] = Math.max(count[next][i], count[now][i] + adder);
                }

                indegrees[next]--;
                if (indegrees[next] == 0) {
                    queue.add(next);
                }
            }
            answer = Math.max(answer, getMax(count[now]));
            for (int[] temp : count) {
                System.out.println(Arrays.toString(temp));
            }
            System.out.println("==============");
        }

        return (visit == N) ? answer : -1;

    }
}
