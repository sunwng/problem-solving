import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        int[][] check = new int[4][2];
        HashMap<String, Integer[]> map = new HashMap<>();
        String[][] type = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        
        map.put("R", new Integer[]{0, 0});
        map.put("T", new Integer[]{0, 1});
        map.put("C", new Integer[]{1, 0});
        map.put("F", new Integer[]{1, 1});
        map.put("J", new Integer[]{2, 0});
        map.put("M", new Integer[]{2, 1});
        map.put("A", new Integer[]{3, 0});
        map.put("N", new Integer[]{3, 1});
        
        
        int N = choices.length;
        for (int i = 0; i < N; i++) {
            if (choices[i] < 4) {
                String strI = survey[i].substring(0, 1);
                check[map.get(strI)[0]][map.get(strI)[1]] += Math.abs(choices[i] - 4);
            } else if (choices[i] > 4) {
                String strI = survey[i].substring(1);
                check[map.get(strI)[0]][map.get(strI)[1]] += Math.abs(choices[i] - 4);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (check[i][1] > check[i][0]) {
                answer += type[i][1];
            } else {
                answer += type[i][0];
            }
        }
        
        
        return answer;
    }
}