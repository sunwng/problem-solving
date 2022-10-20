import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();

        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            days[i] = (int) Math.ceil((float) (100 - progresses[i]) / speeds[i]);
        }
        System.out.println(Arrays.toString(days));
        int curI = days[0];
        int count = 1;
        for (int i = 1; i < progresses.length; i++) {
            if (days[i] > curI) {
                curI = days[i];
                answer.add(count);
                count = 1;
            } else {
                count++;
            }
        }
        answer.add(count);

        return answer.stream().mapToInt(i -> i).toArray();
    }
}