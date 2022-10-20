import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] count = new int[3];
        int[] ansOne = {1, 2, 3, 4, 5};
        int[] ansTwo = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] ansThree = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == ansOne[i%5]) {
                count[0] += 1;
            }
            if (answers[i] == ansTwo[i%8]) {
                count[1] += 1;
            }
            if (answers[i] == ansThree[i%10]) {
                count[2] += 1;
            }
        }
        ArrayList<Integer> maxCheck = new ArrayList<>();
        maxCheck.add(1);
        for (int i = 1; i < 3; i++) {
            if (count[i] > count[maxCheck.get(0)-1]) {
                maxCheck.remove(0);
                maxCheck.add(i+1);
            } else if (count[i] == count[maxCheck.get(0)-1]) {
                maxCheck.add(i+1);
            }
        }
        int[] answer = maxCheck.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}