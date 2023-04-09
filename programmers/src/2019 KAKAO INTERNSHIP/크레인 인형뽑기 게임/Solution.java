import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        int answer = 0;
        
        List<Queue<Integer>> lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            lines.add(new LinkedList<>());
        }
        for (int[] boardI : board) {
            for (int i = 0; i < N; i++) {
                if (boardI[i] == 0) continue;
                lines.get(i).add(boardI[i]);
            }
        }
        
        Stack<Integer> basket = new Stack<>();
        for (int move: moves) {
            if (lines.get(move - 1).isEmpty()) continue;
            int candidate = lines.get(move - 1).poll();
            if (basket.isEmpty()) {
                basket.push(candidate);
            } else if (basket.peek() == candidate) {
                basket.pop();
                answer += 2;
            } else {
                basket.push(candidate);
            }
        }
        
        return answer;
    }
}