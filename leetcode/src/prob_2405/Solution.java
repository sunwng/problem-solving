package prob_2405;

class Solution {
    public int partitionString(String s) {
        int answer = 0;
        String prev = s.substring(0, 1);

        for (String alphabet : s.split("")) {
            if (prev.contains(alphabet)) {
                prev = alphabet;
                answer++;
            } else {
                prev += alphabet;
            }
        }
        return answer;
    }
}
