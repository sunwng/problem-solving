import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        ArrayList<String> members = new ArrayList<>();
        for (String member : enroll) {
            members.add(member);
        }
        int totalNum = enroll.length;
        int[] parent = new int[totalNum];
        for (int i = 0; i < totalNum; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < totalNum; i++) {
            if (referral[i].equals("-")) continue;
            int parentIndex = members.indexOf(referral[i]);
            parent[i] = parentIndex;
        }
        int[] answer = new int[totalNum];
        for (int i = 0; i < seller.length; i++) {
            String curName = seller[i];
            int curIndex = members.indexOf(curName);
            double curIncome = amount[i] * 100;
            while (true) {
                int nextIncome = (int) (curIncome *  0.1);
                if (nextIncome == 0) {
                    answer[curIndex] += curIncome;
                } else {
                    answer[curIndex] += (int) (curIncome - nextIncome);
                }
                if (curIndex == parent[curIndex]) {
                    break;
                }
                curIndex = parent[curIndex];
                curIncome = nextIncome;
            }
        }
        return answer;
    }
}