import java.util.*;

class Solution {
    Map<Integer, int[]> map;
    
    int getDistance(int[] curent, int[] target) {
        return Math.abs(curent[0] - target[0]) + Math.abs(curent[1] - target[1]);
    }
    
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put(i + 1, new int[] {i / 3, i % 3});
        }
        map.put(0, new int[] {3, 1});
        int[] left = new int[] {3, 0};
        int[] right = new int[] {3, 2};
        
        for (int number : numbers) {
            if (number % 3 == 1) {
                left = map.get(number);
                answer.append("L");
            } else if (number % 3 == 0 && number != 0) {
                right = map.get(number);
                answer.append("R");
            } else {
                int fromLeft = getDistance(left, map.get(number));
                int fromRight = getDistance(right, map.get(number));
                if (fromLeft < fromRight) {
                    left = map.get(number);
                    answer.append("L");
                } else if (fromLeft > fromRight) {
                    right = map.get(number);
                    answer.append("R");
                } else {
                    if ("right".equals(hand)) {
                        right = map.get(number);
                        answer.append("R");
                    } else {
                        left = map.get(number);
                        answer.append("L"); 
                    }
                }
            }
        }
        
        return answer.toString();
    }
}