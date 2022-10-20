import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> scovList = new PriorityQueue<>();
        for (int i : scoville) {
            scovList.add(i);
        }

        while (scovList.peek() < K) {
            if (scovList.size() == 1) {
                return -1;
            }
            int temp = scovList.poll() + scovList.poll() * 2;
            scovList.add(temp);
            answer += 1;
        }

        return answer;
    }
}