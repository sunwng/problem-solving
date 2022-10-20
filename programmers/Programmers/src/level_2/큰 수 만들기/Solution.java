import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int totalSize = number.length();
        int demandSize = totalSize - k;
        String[] answerArr = new String[demandSize];
        int curIdx = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < demandSize; i++) {
            for (int j = curIdx; j <= k; j++) {
                int curNum = number.charAt(j) - '0';
                if (curNum > max) {
                    max = curNum;
                    curIdx = j + 1;
                }
            }
            answerArr[i] = Integer.toString(max);
            max = Integer.MIN_VALUE;
            k++;
        }
        answer = String.join("", answerArr);

        return answer;
    }
}