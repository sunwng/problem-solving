import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] curIndex = new int[3];
        for (int i = 0; i < commands.length; i++) {
            curIndex = commands[i];
            int[] temp = Arrays.copyOfRange(array, curIndex[0]-1, curIndex[1]);
            Arrays.sort(temp);
            answer[i] = temp[curIndex[2]-1];
        }
        return answer;
    }
}