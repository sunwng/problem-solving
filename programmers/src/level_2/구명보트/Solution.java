import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        ArrayList<Integer> sortedPeople = new ArrayList<>();
        for (int peopleI : people) {
            sortedPeople.add(peopleI);
        }
        sortedPeople.sort(Collections.reverseOrder());

        while (!sortedPeople.isEmpty()) {
            int curPeople = sortedPeople.get(0);
            sortedPeople.remove(0);
            Optional<Integer> findResult = sortedPeople.stream().filter(a -> a <= (limit-curPeople)).findFirst();
            if (findResult.isEmpty()) {
                answer += 1;
            } else {
                answer += 1;
                int pairNum = findResult.get();
                sortedPeople.remove((Integer) pairNum);
            }
        }

        return answer;
    }
}