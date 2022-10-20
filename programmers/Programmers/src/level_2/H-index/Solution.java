import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        ArrayList<Integer> citList = new ArrayList<>();
        for (int i : citations) {
            citList.add(i);
        }
        Collections.sort(citList, Collections.reverseOrder());
        for (int j = citList.get(0); j >= 0; j--) {
            int threshold = j;
            int count = (int) citList.stream().filter(a -> a >= threshold).count();
            if (count >= j) {
                answer = j;
                break;
            }
        }

        return answer;
    }
}