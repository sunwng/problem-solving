import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int[] longSet = new int[sizes.length];
        int[] shortSet = new int[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] >= sizes[i][1]) {
                longSet[i] = sizes[i][0];
                shortSet[i] = sizes[i][1];
            } else {
                longSet[i] = sizes[i][1];
                shortSet[i] = sizes[i][0];
            }
        }
        Arrays.sort(longSet);
        Arrays.sort(shortSet);


        int answer = longSet[sizes.length-1] * shortSet[sizes.length-1];
        return answer;
    }
}