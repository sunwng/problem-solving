import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = -1;
        int[] wanho = scores[0];
        int wanhoSum = wanho[0] + wanho[1];

        Arrays.sort(scores, (int[] o1, int[] o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o2[0] - o1[0];
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (int[] o1, int[] o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1])
        );
        pq.add(scores[0]);
        int prev = scores[0][1];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] >= prev) {
                pq.add(scores[i]);
                prev = Math.max(prev, scores[i][1]);
            }
        }

        if (pq.contains(wanho)) {
            answer = 1;
            while (true) {
                int[] current = pq.poll();
                if ((current[0] + current[1]) == wanhoSum) break;
                answer++;
            }

        }
        return answer;
    }
}